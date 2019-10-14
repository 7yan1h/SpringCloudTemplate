package top.ryan1h.springcloud.template.common.log;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用日志切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogConfiguration.class)
public @interface EnableLog {
}
