package top.ryan1h.springcloud.template.common.util.validation.annotation;

import top.ryan1h.springcloud.template.common.util.validation.validator.CollectionLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 限制集合的长度
 *
 * @author huangxin
 * @date 2019/8/14
 */
@Documented
@Constraint(validatedBy = CollectionLengthValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionLength {
    String message() default "属性长度不合法";

    int min() default 1;

    int max() default 2147483647;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
