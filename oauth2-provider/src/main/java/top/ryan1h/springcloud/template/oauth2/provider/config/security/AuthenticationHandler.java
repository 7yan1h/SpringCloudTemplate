package top.ryan1h.springcloud.template.oauth2.provider.config.security;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;
import top.ryan1h.springcloud.template.common.object.Result;
import top.ryan1h.springcloud.template.common.util.web.request.ServletUtils;
import top.ryan1h.springcloud.template.oauth2.provider.consts.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录,退出处理
 */
@Slf4j
public class AuthenticationHandler implements AuthenticationEntryPoint, AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    /**
     * 处理未登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ServletUtils.responseJson(httpServletResponse, Result.error(MsgEnum.E00004, "登录URL：" + SecurityProperties.LOGIN_URL));
    }

    /**
     * 处理登录失败
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String messageEnum = e.getMessage();
        try {
            MsgEnum msgEnum = JSON.parseObject(messageEnum, MsgEnum.class);
            ServletUtils.responseJson(httpServletResponse, Result.error(msgEnum));
        } catch (Exception ex) {
            ServletUtils.responseJson(httpServletResponse, Result.error(MsgEnum.E00008));
        }
    }

    /**
     * 处理登录成功
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Object object = authentication.getPrincipal();
        ServletUtils.responseJson(httpServletResponse, Result.success(object, MsgEnum.S00001));
    }

    /**
     * 处理登出成功
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ServletUtils.responseJson(httpServletResponse, Result.success(MsgEnum.S00002));
    }
}