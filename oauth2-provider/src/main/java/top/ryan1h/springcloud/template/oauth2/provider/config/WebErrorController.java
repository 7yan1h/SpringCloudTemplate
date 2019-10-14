package top.ryan1h.springcloud.template.oauth2.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;
import top.ryan1h.springcloud.template.common.object.Result;
import top.ryan1h.springcloud.template.common.util.web.request.ServletUtils;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class WebErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public Result error() {
        log.error("===>访问未知路径,ip:{}", ServletUtils.getIPAddress(httpServletRequest));

        return Result.error(MsgEnum.E00001);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedException(AccessDeniedException e) {
        return Result.error(MsgEnum.E00006);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Result.error(MsgEnum.E00013);
    }

    @ExceptionHandler(FileUploadException.class)
    public Result fileUploadException(FileUploadException e) {
        return Result.error(MsgEnum.E00003);
    }

    @ExceptionHandler(Throwable.class)
    public Result throwable(Throwable e) {
        log.error("===>未知的异常信息", e);

        return Result.error(MsgEnum.E00000);
    }
}
