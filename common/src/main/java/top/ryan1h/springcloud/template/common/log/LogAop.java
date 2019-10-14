package top.ryan1h.springcloud.template.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * 方法入参日志
 *
 * @author huangxin
 * @date 2019/8/22
 */
@Aspect
@Slf4j
public class LogAop {

    @Pointcut("@annotation(top.ryan1h.springcloud.template.common.log.Log)")
    public void printLog() {
    }

    /**
     * 方法执行前打印日志
     *
     * @param joinPoint 切点
     * @author huangxin
     * @date 2019年8月9日
     */
    @Before("printLog()")
    private void logMethodInfoBeforeInvoke(JoinPoint joinPoint) {
        StringBuilder info = new StringBuilder();

        String joinPointClassName = joinPoint.getTarget().getClass().getName();
        info.append("===>");
        info.append(joinPointClassName);
        info.append(".");

        String joinPointMethodName = joinPoint.getSignature().getName();
        info.append(joinPointMethodName);
        info.append("():入参为");

        Object[] args = joinPoint.getArgs();
        info.append(Arrays.toString(args));

        log.info(info.toString());
    }
}