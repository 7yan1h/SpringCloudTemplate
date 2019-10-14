package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.BetweenStart;
import top.ryan1h.springcloud.template.common.mybatis.utils.Between;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

import java.util.Map;

/**
 * 处理@BetweenStart
 *
 * @author 59941
 * @date 2019/7/24 20:31
 */
public class BetweenStartHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = BetweenStart.class;

    private static volatile BetweenStartHandler instance;

    private BetweenStartHandler() {
        super(ANNOTATION_CLASS);
    }

    public static BetweenStartHandler getInstance() {
        if (instance == null) {
            synchronized (BetweenStartHandler.class) {
                if (instance == null) {
                    instance = new BetweenStartHandler();
                }
            }
        }

        return instance;
    }

    @Override
    protected void handleColumnNameAndColumnValue(String columnName, Object columnValue, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        Map<String, Between> betweenMap = annotationHandlerChain.getBetweenMap();

        if (betweenMap.get(columnName) != null) {
            Between between = betweenMap.get(columnName);
            if (between.getBetweenEnd() != null) {
                conditionWrapper.between(columnName, columnValue, between.getBetweenEnd());
                betweenMap.remove(columnName);
            } else {
                between.setBetweenStart(columnValue);
            }
        } else {
            Between between = new Between();
            between.setBetweenStart(columnValue);
            betweenMap.put(columnName, between);
        }
    }
}