package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.GT;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@GT
 *
 * @author 59941
 * @date 2019/7/22 4:23
 */
public class GTHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = GT.class;

    private static volatile GTHandler instance;

    private GTHandler() {
        super(ANNOTATION_CLASS);
    }

    public static GTHandler getInstance() {
        if (instance == null) {
            synchronized (GTHandler.class) {
                if (instance == null) {
                    instance = new GTHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.gt(columnName, columnValue);
    }
}