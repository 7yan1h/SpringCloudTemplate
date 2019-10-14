package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.Like;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

/**
 * 处理@Like
 *
 * @author 59941
 * @date 2019/7/22 4:26
 */
public class LikeHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = Like.class;

    private static volatile LikeHandler instance;

    private LikeHandler() {
        super(ANNOTATION_CLASS);
    }

    public static LikeHandler getInstance() {
        if (instance == null) {
            synchronized (LikeHandler.class) {
                if (instance == null) {
                    instance = new LikeHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        conditionWrapper.like(columnName, columnValue);
    }
}