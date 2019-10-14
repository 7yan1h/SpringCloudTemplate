package top.ryan1h.springcloud.template.common.util.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import top.ryan1h.springcloud.template.common.context.StaticBeans;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

@Slf4j
public class ServletUtils {
    /**
     * 即使通过反向代理，也可以获取用户的IP.
     * nginx配置tomcat的location中增加:proxy_set_header x-forwarded-for  $remote_addr;
     *
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        // X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            // X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取数字类型参数值
     *
     * @param request 请求体
     * @param name    参数名
     * @param <T>     Number的实现类型
     * @return 数字类型值
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T getNumberParam(ServletRequest request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            try {
                return (T) NumberFormat.getInstance().parse(value);
            } catch (ParseException e) {
                log.warn("Request param '{} = {}' isn't a number.", name, value);
            }
        }
        return null;
    }

    /**
     * 从请求中获取输入的字符串
     *
     * @return 请求体中的字符串
     */
    public static String getRequestText(HttpServletRequest request) {
        byte[] bytes = new byte[request.getContentLength()];
        try {
            InputStream is = request.getInputStream();
            int length = is.read(bytes);
            return length == 0 ? null : new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据请求获取响应类型
     *
     * @param request 请求体
     * @return 响应类型
     */
    public static List<MediaType> getMediaTypes(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        List<MediaType> types = MediaType.parseMediaTypes(accept);
        String contentType = request.getContentType();
        if (contentType != null && contentType.trim().length() > 0) {
            types.add(MediaType.parseMediaType(request.getContentType()));
        }
        return types;
    }

    /**
     * 从请求中获取实体对象
     *
     * @param request 请求内容
     * @param clz     对象类
     * @param <T>     对象类型
     * @return 获取的实体对象或null
     */
    public static <T> T getRequestBody(HttpServletRequest request, Class<T> clz) throws IOException {
        String text = getRequestText(request);
        if (StringUtils.isNotBlank(text)) {
            text = text.trim();
            ObjectMapper mapper;
            if (text.startsWith("<")) {
                mapper = StaticBeans.getXmlMapper();
            } else {
                mapper = StaticBeans.getObjectMapper();
            }
            return mapper.readValue(text, clz);
        }
        return null;
    }

    /**
     * 利用 ServletResponse 输出字符串
     *
     * @param response 上下文的 ServletResponse 对象
     * @param text     需要输出的字符串
     */
    public static void responseText(ServletResponse response, String text) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        try (PrintWriter writer = response.getWriter()) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            log.error("HttpResponse输出错误：" + e.getMessage(), e);
        }
    }

    /**
     * 根据请求类型输出对象
     *
     * @param request  请求体
     * @param response 上下文的 ServletResponse 对象
     * @param object   需要输出的对象
     * @return 是否已输出
     */
    public static boolean responseBody(HttpServletRequest request, HttpServletResponse response, Object object) {
        List<MediaType> types = getMediaTypes(request);
        if (types != null && types.size() > 0) {
            for (MediaType type : types) {
                if (type.getSubtype().equalsIgnoreCase("json")) {
                    responseJson(response, object);
                    return true;
                }
                if (type.getSubtype().matches("([A-Za-z]+\\+)?xml|html")) {
                    responseXml(response, object);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 利用 ServletResponse 输出对象
     *
     * @param response     上下文的 ServletResponse 对象
     * @param object       需要输出的对象
     * @param objectMapper 输出对象的序列化 Mapper
     */
    public static void responseBody(ServletResponse response, Object object, ObjectMapper objectMapper) {
        if (object == null) {
            responseText(response, "null");
        }
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
            objectMapper.writeValue(response.getWriter(), object);
        } catch (IOException e) {
            log.error("Object转化为字符串错误：" + e.getMessage(), e);
        }
    }

    /**
     * 利用 HttpServletResponse 输出二进制文件
     *
     * @param response 应答对象
     * @param bytes    需要输出的数据
     * @param name     输出显示的文件名称
     * @throws IOException 输出错误
     */
    public static void responseBytes(HttpServletResponse response, byte[] bytes, String name) throws IOException {
        if (response.isCommitted()) return;
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        OutputStream stream = response.getOutputStream();
        stream.write(bytes);
        stream.close();
    }

    /**
     * 根据请求类型输出对象，如果未找到合适的输出方式，则输出为 JSON
     *
     * @param request  请求体
     * @param response 上下文的 ServletResponse 对象
     * @param object   需要输出的对象
     */
    public static void responseBodyOrJson(HttpServletRequest request, HttpServletResponse response, Object object) {
        if (!responseBody(request, response, object)) {
            responseJson(response, object);
        }
    }

    /**
     * 通过 ServletResponse 输出对象为 Json 字符串
     *
     * @param response 上下文的 ServletResponse 对象
     * @param object   需要输出的对象
     */
    public static void responseJson(ServletResponse response, Object object) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        responseBody(response, object, StaticBeans.getObjectMapper());
    }


    /**
     * 通过 ServletResponse 输出对象为 Xml 字符串
     *
     * @param response 上下文的 ServletResponse 对象
     * @param object   需要输出的对象
     */
    public static void responseXml(ServletResponse response, Object object) {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        responseBody(response, object, StaticBeans.getXmlMapper());
    }
}
