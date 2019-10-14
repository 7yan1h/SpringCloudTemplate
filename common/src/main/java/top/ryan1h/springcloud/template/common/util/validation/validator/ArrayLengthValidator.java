package top.ryan1h.springcloud.template.common.util.validation.validator;

import top.ryan1h.springcloud.template.common.util.validation.annotation.ArrayLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 限制数组的长度，第二个泛型参数要对应被注解的值的类型
 *
 * @author huangxin
 * @date 2019/8/14
 */
public class ArrayLengthValidator implements ConstraintValidator<ArrayLength, Object[]> {

    private ArrayLength arrayLength;

    /**
     * 可以用来获取注解对象
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ArrayLength constraintAnnotation) {
        this.arrayLength = constraintAnnotation;
    }

    /**
     * @param value   要验证的对象,也就是被注解的对象
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        int max = arrayLength.max();
        int min = arrayLength.min();

        int targetLength = value.length;
        return targetLength >= min && targetLength <= max;
    }
}
