package top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.annotation.property.BetweenEnd;
import top.ryan1h.springcloud.template.common.mybatis.utils.Between;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AbstractAnnotationHandler;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;

import java.util.Map;

/**
 * 处理@BetweenEnd
 *
 * @author 59941
 * @date 2019/7/24 20:40
 */
public class BetweenEndHandler<C, T> extends AbstractAnnotationHandler<C, T> {
    private static final Class ANNOTATION_CLASS = BetweenEnd.class;

    private static volatile BetweenEndHandler instance;

    private BetweenEndHandler() {
        super(ANNOTATION_CLASS);
    }

    public static BetweenEndHandler getInstance() {
        if (instance == null) {
            synchronized (BetweenEndHandler.class) {
                if (instance == null) {
                    instance = new BetweenEndHandler();
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
            if (between.getBetweenStart() != null) {
                conditionWrapper.between(columnName, between.getBetweenStart(), columnValue);
                betweenMap.remove(columnName);
            } else {
                between.setBetweenEnd(columnValue);
            }
        } else {
            Between between = new Between();
            between.setBetweenEnd(columnValue);
            betweenMap.put(columnName, between);
        }
    }
}
