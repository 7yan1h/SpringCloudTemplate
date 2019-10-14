package top.ryan1h.springcloud.template.common.util.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import top.ryan1h.springcloud.template.common.util.ReflectUtils;

import java.util.*;

public class BeanCopier {

    /**
     * 非集合对象间拷贝,适合可以直接创建des对象
     *
     * @param src
     * @param des
     * @param <S>
     * @param <D>
     */
    public static <S, D> D copy(S src, D des) {
        if (src == null || des == null) {
            return null;
        }

        org.springframework.cglib.beans.BeanCopier beanCopier = org.springframework.cglib.beans.BeanCopier.create(src.getClass(), des.getClass(), false);
        beanCopier.copy(src, des, null);

        return des;
    }

    /**
     * 非集合对象间拷贝,性能较低,只适合只能用反射创建des对象
     *
     * @param src
     * @param desClass
     * @param <S>
     * @param <D>
     */
    public static <S, D> D copy(S src, Class<D> desClass) {
        if (src == null || desClass == null) {
            return null;
        }

        org.springframework.cglib.beans.BeanCopier beanCopier = org.springframework.cglib.beans.BeanCopier.create(src.getClass(), desClass, false);
        D des = ReflectUtils.newInstance(desClass);
        beanCopier.copy(src, des, null);

        return des;
    }

    /**
     * 集合对象间拷贝，需要先手动给desCollection添加数量为srcCollection元素个数的元素
     * <p>srcCollection.forEach(s -> desCollection.add(new des()));</p>
     *
     * @param srcCollection
     * @param desCollection
     * @param <S>
     * @param <D>
     */
    public static <S, D> Collection<D> copy(Collection<S> srcCollection, Collection<D> desCollection) {
        if (CollectionUtils.isEmpty(srcCollection) || CollectionUtils.isEmpty(desCollection) || srcCollection.size() != desCollection.size()) {
            return null;
        }

        Iterator<D> desIterator = desCollection.iterator();

        for (S src : srcCollection) {
            copy(src, desIterator.next());
        }

        return desCollection;
    }

    /**
     * 集合对象间拷贝,只适合只能用反射创建desCollection中的元素
     *
     * @param srcCollection
     * @param desClass
     * @param <S>
     * @param <D>
     */
    public static <S, D> Collection<D> copy(Collection<S> srcCollection, Class<D> desClass) {
        if (CollectionUtils.isEmpty(srcCollection) || desClass == null) {
            return null;
        }

        Collection<D> desCollection;

        if (srcCollection.getClass().isAssignableFrom(ArrayList.class)) {
            desCollection = new ArrayList<>();
        } else if (srcCollection.getClass().isAssignableFrom(HashSet.class)) {
            desCollection = new HashSet<>();
        } else if (srcCollection.getClass().isAssignableFrom(LinkedList.class)) {
            desCollection = new LinkedList<>();
        } else {
            return null;
        }

        srcCollection.forEach(src -> {
            D des = copy(src, desClass);
            desCollection.add(des);
        });

        return desCollection;
    }

    /**
     * 深度拷贝一个副本
     *
     * @param src 被拷贝的对象
     * @param <T> 被拷贝对象的类型
     * @return 生成的对象
     */
    public static <T> T deepClone(T src) {
        return (T) JSON.parseObject(JSON.toJSONString(src), src.getClass());
    }
}
