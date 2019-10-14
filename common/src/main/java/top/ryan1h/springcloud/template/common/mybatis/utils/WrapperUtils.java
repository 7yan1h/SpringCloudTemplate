package top.ryan1h.springcloud.template.common.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import top.ryan1h.springcloud.template.common.mybatis.dto.PageAndSort;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.AnnotationHandlerChain;
import top.ryan1h.springcloud.template.common.mybatis.utils.handler.impl.*;

import java.lang.reflect.Field;

/**
 * Wrapper操作类
 *
 * @author 59941
 * @date 2019/4/25 14:12
 */
public class WrapperUtils {

    /**
     * <p>根据{@link C}类中的属性上的注解添加查询条件.</p>
     * <p><b>注意:</b>C类中继承超类的属性上的注解不会进行判断</p>
     *
     * @param condition 含有查询注解的对象
     * @param <T>       Bean的类型
     * @param <C>       condition属性的类型
     * @return
     */
    public static <T, C> QueryWrapper<T> getConditionWrapper(C condition) {
        if (condition == null) {
            throw new IllegalArgumentException("condition对象不能为null");
        }

        QueryWrapper<T> conditionWrapper = new QueryWrapper<>();
        Field[] conditionFields = condition.getClass().getDeclaredFields();

        AnnotationHandlerChain<C, T> wrapperHandlerChain = new AnnotationHandlerChain<>();
        wrapperHandlerChain.addHandler(EqualHandler.getInstance());
        wrapperHandlerChain.addHandler(GEHandler.getInstance());
        wrapperHandlerChain.addHandler(GTHandler.getInstance());
        wrapperHandlerChain.addHandler(LEHandler.getInstance());
        wrapperHandlerChain.addHandler(LTHandler.getInstance());
        wrapperHandlerChain.addHandler(LikeHandler.getInstance());
        wrapperHandlerChain.addHandler(LikeLeftHandler.getInstance());
        wrapperHandlerChain.addHandler(LikeRightHandler.getInstance());
        wrapperHandlerChain.addHandler(BetweenStartHandler.getInstance());
        wrapperHandlerChain.addHandler(BetweenEndHandler.getInstance());

        for (Field conditionField : conditionFields) {
            wrapperHandlerChain.setHandlerIndex(AnnotationHandlerChain.INIT_HANDLER_INDEX);

            if (conditionField.getDeclaredAnnotations().length == 0) {
                continue;
            }

            wrapperHandlerChain.handleAnnotation(conditionField, condition, conditionWrapper, wrapperHandlerChain);
        }

        return conditionWrapper;
    }

    /**
     * <p>首先构造一个含有排序条件的QueryWrapper对象,然后根据{@link C}类中的属性上的注解添加查询条件.</p>
     * <p><b>注意:</b>C类中继承超类的属性上的注解不会进行判断</p>
     *
     * @param queryParam
     * @param <T>        Bean的类型
     * @param <Q>        排序和分页
     * @return
     */
    public static <T, Q extends PageAndSort> QueryWrapper<T> getWrapper(Q queryParam) {
        QueryWrapper<T> conditionWrapper = getConditionWrapper(queryParam);

        return addSort(conditionWrapper, queryParam);
    }

    /**
     * 给QueryWrapper对象添加排序条件
     *
     * @param wrapper
     * @param queryParam
     * @return
     */
    private static <T, Q extends PageAndSort> QueryWrapper<T> addSort(QueryWrapper<T> wrapper, Q queryParam) {
        String[] sort = queryParam.getSort();

        // 添加排序条件
        if (sort != null && sort.length > 0) {
            for (String sortItem : sort) {
                if (StringUtils.isNotBlank(sortItem)) {
                    // 得到最后一个中划线的索引
                    int lastMiddleLineIndex = sortItem.lastIndexOf('-');
                    // 该中划线不是第一个和最后一个字符
                    if (lastMiddleLineIndex > 0 && lastMiddleLineIndex != sort.length - 1) {
                        String sortColumn = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortItem.substring(0, lastMiddleLineIndex));
                        String sortType = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, sortItem.substring(lastMiddleLineIndex + 1));

                        // 添加升序列
                        if (SqlKeyword.ASC.getSqlSegment().equals(sortType)) {
                            wrapper.orderByAsc(sortColumn);
                            continue;
                        }

                        // 添加降序列
                        if (SqlKeyword.DESC.getSqlSegment().equals(sortType)) {
                            wrapper.orderByDesc(sortColumn);
                        }
                    }
                }
            }
        }

        return wrapper;
    }

    /**
     * 构造一个只含有排序条件的QueryWrapper对象
     *
     * @param queryParam
     * @return
     */
    public static <T, Q extends PageAndSort> QueryWrapper<T> getSortWrapper(Q queryParam) {
        return addSort(new QueryWrapper<>(), queryParam);
    }

}