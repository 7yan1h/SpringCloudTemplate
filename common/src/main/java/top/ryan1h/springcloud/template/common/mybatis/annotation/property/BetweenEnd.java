package top.ryan1h.springcloud.template.common.mybatis.annotation.property;

import java.lang.annotation.*;

/**
 * 使用 between .. and 比较的属性
 *
 * @author 59941
 * @date 2019/4/25 21:36
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BetweenEnd {
    /**
     * 数据库的列名
     *
     * @return
     */
    String value();
}
