package top.ryan1h.springcloud.template.common.util.validation.validator;

import top.ryan1h.springcloud.template.common.util.validation.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号验证器
 *
 * @author huangxin
 * @date 2019/8/13
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern phonePattern = Pattern.compile("^1[3456789]\\d{9}$");

    /**
     * @param value   要验证的对象,也就是被注解的对象
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = phonePattern.matcher(value);

        return matcher.find();
    }
}
