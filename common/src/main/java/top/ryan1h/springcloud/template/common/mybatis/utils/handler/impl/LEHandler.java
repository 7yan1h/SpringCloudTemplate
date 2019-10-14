package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.LE;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@LE
 *
 * @author 59941
 * @date 2019/7/22 4:26
 */
public class LEHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = LE.class;

    private static volatile LEHandler instance;

    private LEHandler() {
        super(ANNOTATION_CLASS);
    }

    public static LEHandler getInstance() {
        if (instance == null) {
            synchronized (LEHandler.class) {
                if (instance == null) {
                    instance = new LEHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.le(columnName, columnValue);
    }
}
