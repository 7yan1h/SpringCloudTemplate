package top.ryan1h.springcloud.template.common.util;

import org.springframework.util.CollectionUtils;
import top.ryan1h.springcloud.template.common.util.bean.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>将对象A{id,pId,value}的列表
 * 生成树形结构对象B{id,pId,value,List<B> children}的工具</p>
 */
public class TreeUtils {

    /**
     * 默认的id属性名
     */
    private static String DEFAULT_ID_NAME = "id";

    /**
     * 默认的父id属性名
     */
    private static String DEFAULT_P_ID_NAME = "pId";

    /**
     * 默认的子节点属性名
     */
    private static String DEFAULT_CHILDREN_NAME = "children";

    /**
     * 手动配置属性名
     *
     * @param id
     * @param pId
     * @param children
     */
    public static void config(String id, String pId, String children) {
        TreeUtils.DEFAULT_ID_NAME = id;
        TreeUtils.DEFAULT_P_ID_NAME = pId;
        TreeUtils.DEFAULT_CHILDREN_NAME = children;
    }

    /**
     * 生成一个根节点id值为rootId的n叉树
     *
     * @param sList  要创建树形结构的列表
     * @param rootId 根节点id
     * @param dClass 树节点对象
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D getTree(List<S> sList, Long rootId, Class<D> dClass) {
        if (CollectionUtils.isEmpty(sList) || rootId == null || dClass == null) {
            return null;
        }

        // 创建根节点对象
        D root = ReflectUtils.newInstance(dClass);
        ReflectUtils.setFieldValue(root, DEFAULT_ID_NAME, rootId);
        // 找到列表中根节点对应的对象
        Optional<S> rootBean = sList.stream()
                .filter(s -> ReflectUtils.getFieldValue(s, DEFAULT_ID_NAME).equals(rootId))
                .findFirst();
        // 如果列表中存在根节点对应的对象,就给根节点属性赋值
        rootBean.ifPresent(s -> BeanCopier.copy(s, root));

        // 得到按p_id分组的Map
        Map<Long, List<S>> pIdMap = sList.stream().collect(Collectors.groupingBy(s -> (Long) ReflectUtils.getFieldValue(s, DEFAULT_P_ID_NAME)));

        if (CollectionUtils.isEmpty(pIdMap)) {
            return root;
        } else {
            // 得到根节点的子节点列表
            List<S> childrenList = pIdMap.get(rootId);
            if (CollectionUtils.isEmpty(childrenList)) {
                return root;
            }
            // 递归创建根节点的所有子节点对象
            List<D> children = recursion(childrenList, pIdMap, dClass);

            // 设置根节点的子节点属性
            ReflectUtils.setFieldValue(root, DEFAULT_CHILDREN_NAME, children);
        }

        return root;
    }


    /**
     * 判断node节点是否有子节点
     *
     * @param pIdMap
     * @param node
     * @param <S>
     * @return
     */
    private static <S> boolean hasChild(Map<Long, List<S>> pIdMap, S node) {
        return pIdMap.containsKey(ReflectUtils.getFieldValue(node, DEFAULT_ID_NAME));
    }

    /**
     * 创建childList中的节点
     *
     * @param childrenList 子节点列表
     * @param pIdMap
     * @param dClass
     * @param <S>
     * @param <D>
     * @return
     */
    private static <S, D> List<D> recursion(List<S> childrenList, Map<Long, List<S>> pIdMap, Class<D> dClass) {
        // 由childList生成的子节点列表
        List<D> treeList = new ArrayList<>();
        // 遍历childList中的每一个节点,并创建节点
        for (S children : childrenList) {
            // 创建一个子节点对象
            D node = ReflectUtils.newInstance(dClass);
            // 复制属性
            BeanCopier.copy(children, node);
            // 添加该节点
            treeList.add(node);
            // 判断创建的节点是否有子节点，有就创建它的子节点
            if (hasChild(pIdMap, children)) {
                // 得到子节点列表
                List<S> sList = pIdMap.get(ReflectUtils.getFieldValue(children, DEFAULT_ID_NAME));
                // 由sList生成的子节点列表
                List<D> sListChildren = recursion(sList, pIdMap, dClass);
                ReflectUtils.setFieldValue(node, DEFAULT_CHILDREN_NAME, sListChildren);
            }
        }

        return treeList;
    }
}
