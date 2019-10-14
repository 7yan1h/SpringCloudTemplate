package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.LikeLeft;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@LikeLeft
 *
 * @author 59941
 * @date 2019/7/22 4:27
 */
public class LikeLeftHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = LikeLeft.class;

    private static volatile LikeLeftHandler instance;

    private LikeLeftHandler() {
        super(ANNOTATION_CLASS);
    }

    public static LikeLeftHandler getInstance() {
        if (instance == null) {
            synchronized (LikeLeftHandler.class) {
                if (instance == null) {
                    instance = new LikeLeftHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.likeLeft(columnName, columnValue);
    }
}