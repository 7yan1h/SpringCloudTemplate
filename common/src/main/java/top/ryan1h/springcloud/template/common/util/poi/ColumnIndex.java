package top.ryan1h.springcloud.template.common.util.poi;

import java.lang.annotation.*;

/**
 * 属性在Excel中对应的列的索引号,从0开始
 *
 * @author huangxin
 * @date 2019/8/19
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnIndex {
    /**
     * 索引号
     *
     * @return
     */
    int value();
}
