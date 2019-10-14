package top.ryan1h.springcloud.template.common.mybatis.utils.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import top.ryan1h.springcloud.template.common.util.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 抽象注解处理器
 *
 * @author 59941
 * @date 2019/7/22 4:01
 */
public abstract class AbstractAnnotationHandler<C, T> implements AnnotationHandler<C, T> {
    private static final String ANNOTATION_PROPERTY_NAME = "value";

    private Class<Annotation> annotationClass;

    public AbstractAnnotationHandler(Class<Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    /**
     * 处理对象属性上的SQL条件注解
     *
     * @param conditionField         被处理的字段
     * @param condition              字段所在的对象
     * @param conditionWrapper       QueryWrapper
     * @param annotationHandlerChain 注解处理链
     */
    @Override
    final public void handleAnnotation(
            Field conditionField,
            C condition,
            QueryWrapper<T> conditionWrapper,
            AnnotationHandlerChain annotationHandlerChain) {
        if (conditionField.isAnnotationPresent(annotationClass)) {
            // 得到属性名
            String fieldName = conditionField.getName();
            // 得到属性值
            Object columnValue = ReflectUtils.getFieldValue(condition, fieldName);
            if (columnValue != null) {
                // 得到通过注解设置的列名
                String columnName = (String) ReflectUtils.getAnnotationValue(conditionField.getDeclaredAnnotation(annotationClass), ANNOTATION_PROPERTY_NAME);
                if (StringUtils.isEmpty(columnName)) {
                    // 未设置列名,则将驼峰格式的属性名转为带下划线作为列名
                    columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
                }
                handleColumnNameAndColumnValue(columnName, columnValue, conditionWrapper, annotationHandlerChain);
            }
        }
    }

    /**
     * 处理列名和列值
     *
     * @param columnName             列名
     * @param columnValue            列值
     * @param conditionWrapper       QueryWrapper
     * @param annotationHandlerChain 注解处理链
     */
    protected abstract void handleColumnNameAndColumnValue(String columnName,
                                                           Object columnValue,
                                                           QueryWrapper<T> conditionWrapper,
                                                           AnnotationHandlerChain annotationHandlerChain);

}
