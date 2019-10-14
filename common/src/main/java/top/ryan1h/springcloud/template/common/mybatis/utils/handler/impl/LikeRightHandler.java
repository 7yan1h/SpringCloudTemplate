package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.LikeRight;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@LikeRight
 *
 * @author 59941
 * @date 2019/7/22 4:29
 */
public class LikeRightHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = LikeRight.class;

    private static volatile LikeRightHandler instance;

    private LikeRightHandler() {
        super(ANNOTATION_CLASS);
    }

    public static LikeRightHandler getInstance() {
        if (instance == null) {
            synchronized (LikeRightHandler.class) {
                if (instance == null) {
                    instance = new LikeRightHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.likeRight(columnName, columnValue);
    }
}