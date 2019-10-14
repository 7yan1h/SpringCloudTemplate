package top.ryan1h.springcloud.template.common.log;

import java.lang.annotation.*;

/**
 * 方法日志注解
 *
 * @author huangxin
 * @date 2019/8/9
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
}
