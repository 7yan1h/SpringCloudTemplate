package top.ryan1h.springcloud.template.common.util.validation.annotation;

import top.ryan1h.springcloud.template.common.util.validation.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author huangxin
 * @date 2019/8/13
 */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "不是正确的手机号格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
