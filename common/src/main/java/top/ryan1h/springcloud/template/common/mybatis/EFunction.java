package top.ryan1h.springcloud.template.common.mybatis;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.function.Function;

/**
 * 用于在JDK {@link Function}与mybatis-plus {@link SFunction}间转换,E即(Extend)
 *
 * @author 59941
 * @date 2019/5/7 14:57
 */
@FunctionalInterface
public interface EFunction<T, R> extends Function<T, R>, SFunction<T, R> {
}
