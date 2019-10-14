package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.LT;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@LT
 *
 * @author 59941
 * @date 2019/7/22 4:25
 */
public class LTHandler<C, T> extends AbstractAnnotationHandler<C, T> {

    private static final Class ANNOTATION_CLASS = LT.class;

    private static volatile LTHandler instance;

    private LTHandler() {
        super(ANNOTATION_CLASS);
    }

    public static LTHandler getInstance() {
        if (instance == null) {
            synchronized (LTHandler.class) {
                if (instance == null) {
                    instance = new LTHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.lt(columnName, columnValue);
    }
}