package top.ryan1h.springcloud.template.common.util.validation.validator;

import top.ryan1h.springcloud.template.common.util.validation.annotation.CollectionLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * 限制Collection的长度
 *
 * @author huangxin
 * @date 2019/8/14
 */
public class CollectionLengthValidator implements ConstraintValidator<CollectionLength, Collection> {
    private CollectionLength collectionLength;

    @Override
    public void initialize(CollectionLength constraintAnnotation) {
        this.collectionLength = constraintAnnotation;
    }

    @Override
    public boolean isValid(Collection value, ConstraintValidatorContext context) {
        int max = collectionLength.max();
        int min = collectionLength.min();

        int targetLength = value.size();
        return targetLength >= min && targetLength <= max;
    }
}
