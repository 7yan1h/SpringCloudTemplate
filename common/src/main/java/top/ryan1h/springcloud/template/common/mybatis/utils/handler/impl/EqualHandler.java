package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.Equal;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@Equal
 *
 * @author 59941
 * @date 2019/7/22 4:06
 */
public class EqualHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = Equal.class;

    private static volatile EqualHandler instance;

    private EqualHandler() {
        super(ANNOTATION_CLASS);
    }

    public static EqualHandler getInstance() {
        if (instance == null) {
            synchronized (EqualHandler.class) {
                if (instance == null) {
                    instance = new EqualHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.eq(columnName, columnValue);
    }
}
