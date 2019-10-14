package top.ryan1h.springcloud.template.common.mybatis.utils.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;

/**
 * 注解处理器接口
 *
 * @param <C>
 * @param <T>
 */
public interface AnnotationHandler<C, T> {
    /**
     * 处理对象属性上的SQL操作注解
     *
     * @param conditionField         被处理的字段
     * @param condition              字段所在的对象
     * @param conditionWrapper       QueryWrapper
     * @param annotationHandlerChain 注解处理链
     */
    void handleAnnotation(Field conditionField, C condition, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain);

}
