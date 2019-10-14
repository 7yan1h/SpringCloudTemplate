package top.ryan1h.springcloud.template.common.mybatis.utils.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ryan1h.springcloud.template.common.mybatis.utils.Between;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用责任链模式处理字段上的每个注解
 *
 * @author 59941
 * @date 2019/7/22 4:03
 */
public class AnnotationHandlerChain<C, T> implements AnnotationHandler<C, T> {
    public static final Integer INIT_HANDLER_INDEX = 0;

    /**
     * 存放使用between...and的列名和列值
     */
    private final Map<String, Between> betweenMap = new HashMap<>();

    /**
     * 保存所有处理注解的handler
     */
    private List<AbstractAnnotationHandler> annotationHandlers = new ArrayList<>();

    /**
     * 当前handler的索引
     */
    private int handlerIndex;

    public void setHandlerIndex(int handlerIndex) {
        this.handlerIndex = handlerIndex;
    }

    public Map<String, Between> getBetweenMap() {
        return betweenMap;
    }

    public void addHandler(AbstractAnnotationHandler annotationHandler) {
        annotationHandlers.add(annotationHandler);
    }

    @Override
    public void handleAnnotation(Field conditionField, C condition, QueryWrapper<T> conditionWrapper, AnnotationHandlerChain annotationHandlerChain) {
        for (; handlerIndex < annotationHandlers.size(); ) {
            AbstractAnnotationHandler currentAnnotationHandler = annotationHandlers.get(handlerIndex++);
            currentAnnotationHandler.handleAnnotation(conditionField, condition, conditionWrapper, annotationHandlerChain);
        }
    }
}
