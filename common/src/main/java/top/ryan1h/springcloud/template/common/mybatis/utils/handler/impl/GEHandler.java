package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.GE;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@GE
 *
 * @author 59941
 * @date 2019/7/22 4:25
 */
public class GEHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = GE.class;

    private static volatile GEHandler instance;

    private GEHandler() {
        super(ANNOTATION_CLASS);
    }

    public static GEHandler getInstance() {
        if (instance == null) {
            synchronized (GEHandler.class) {
                if (instance == null) {
                    instance = new GEHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.ge(columnName, columnValue);
    }
}