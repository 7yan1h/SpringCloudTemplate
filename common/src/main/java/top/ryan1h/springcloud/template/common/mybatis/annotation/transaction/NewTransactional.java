package top.ryan1h.springcloud.template.common.mybatis.annotation.transaction;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启新事务
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
public @interface NewTransactional {
}
