package top.ryan1h.springcloud.template.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;

/**
 * 反射操作工具
 */
public class ReflectUtils {

    /**
     * <p>1.调用默认构造方法时,注意可能需要显示提供默认构造方法</p>
     * <p>2.调用的有参构造方法上的参数不能是基本类型,否则会返回null</p>
     *
     * @param clazz           要创建对象的Class对象
     * @param constructorArgs 调用有参构造方法时传入的参数
     * @param <D>
     * @return
     */
    public static <D> D newInstance(Class<D> clazz, Object... constructorArgs) {
        if (clazz == null) {
            return null;
        }

        Constructor<D> declaredConstructor;
        // 调用默认构造方法创建对象
        if (constructorArgs == null || constructorArgs.length == 0) {
            try {
                declaredConstructor = clazz.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                return null;
            }

            try {
                return declaredConstructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        } else {
            // 根据参数得到参数的Class数组
            Class<?>[] constructorArgsClassArr = new Class[constructorArgs.length];
            for (int i = 0; i < constructorArgs.length; i++) {
                constructorArgsClassArr[i] = constructorArgs[i].getClass();
            }

            try {
                // 如果要调用的有参构造方法的参数是基本类型,就会找不到这个有参构造方法
                declaredConstructor = clazz.getDeclaredConstructor(constructorArgsClassArr);
            } catch (NoSuchMethodException e) {
                return null;
            }

            try {
                return declaredConstructor.newInstance(constructorArgs);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
    }

    /**
     * 获取对象指定的属性
     *
     * @param o         对象
     * @param fieldName 属性名
     * @return 对应的属性
     */
    public static Field getField(Object o, String fieldName) {
        for (Class clz = o.getClass(); clz != Object.class; clz = clz.getSuperclass()) {
            try {
                return clz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }
        }
        return null;
    }


    /**
     * 获取对象指定属性的值
     *
     * @param o         对象
     * @param fieldName 属性名
     * @return 对应的值
     */
    public static Object getFieldValue(Object o, String fieldName) {
        Field field = getField(o, fieldName);
        if (field == null) return null;

        boolean accessible = field.isAccessible();
        if (!accessible) field.setAccessible(true);
        Object value;
        try {
            value = field.get(o);
            if (!accessible) field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置对象指定属性的值
     *
     * @param o         对象
     * @param fieldName 属性名
     * @param value     需要设置的值
     * @return 是否成功
     */
    public static boolean setFieldValue(Object o, String fieldName, Object value) {
        Field field = getField(o, fieldName);
        if (field == null) return false;

        boolean accessible = field.isAccessible();
        if (!accessible) field.setAccessible(true);
        try {
            field.set(o, value);
            if (!accessible) field.setAccessible(false);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Map<String, Object> getMemberValuesMap(Annotation annotation) {
        if (annotation == null) {
            return null;
        }

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        if (invocationHandler == null) {
            return null;
        }
        Field memberValuesField;
        try {
            memberValuesField = invocationHandler.getClass().getDeclaredField("memberValues");
        } catch (NoSuchFieldException e) {
            return null;
        }
        // 设置访问权限,这样就能获取private属性的值了
        memberValuesField.setAccessible(true);
        try {
            // 保存了注解属性名及属性值的Map
            return (Map<String, Object>) memberValuesField.get(invocationHandler);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 得到注解的属性值
     *
     * @param annotation
     * @param propertyName
     * @return
     */
    public static Object getAnnotationValue(Annotation annotation, String propertyName) {
        Map<String, Object> memberValuesMap = getMemberValuesMap(annotation);
        if (memberValuesMap != null) {
            // 获取注解的属性值
            return memberValuesMap.get(propertyName);
        }

        return null;
    }

    /**
     * 修改注解的属性值
     *
     * @param annotation
     * @param propertyName
     * @param value
     * @return
     */
    public static void setAnnotationValue(Annotation annotation, String propertyName, Object value) {
        Map<String, Object> memberValuesMap = getMemberValuesMap(annotation);
        if (memberValuesMap != null) {
            // 修改注解的属性值
            memberValuesMap.put(propertyName, value);
        }
    }

    /**
     * 获取类上的注解
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationClazz) {
        if (clazz == null || annotationClazz == null) {
            return null;
        }

        if (clazz.isAnnotationPresent(annotationClazz)) {
            return clazz.getDeclaredAnnotation(annotationClazz);
        }

        return null;
    }

    /**
     * 获取方法上的注解
     *
     * @param method
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClazz) {
        if (method == null || annotationClazz == null) {
            return null;
        }

        if (method.isAnnotationPresent(annotationClazz)) {
            return method.getDeclaredAnnotation(annotationClazz);
        }

        return null;
    }

    /**
     * 获取字段上的注解
     *
     * @param field
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClazz) {
        if (field == null || annotationClazz == null) {
            return null;
        }

        if (field.isAnnotationPresent(annotationClazz)) {
            return field.getDeclaredAnnotation(annotationClazz);
        }

        return null;
    }
}
