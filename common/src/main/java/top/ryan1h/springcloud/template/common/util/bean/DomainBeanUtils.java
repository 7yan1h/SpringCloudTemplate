package top.ryan1h.springcloud.template.common.util.bean;

import top.ryan1h.springcloud.template.common.object.BaseBean;
import top.ryan1h.springcloud.template.common.object.LoginUser;

import java.util.Collection;
import java.util.Date;

public class DomainBeanUtils {
    /**
     * 添加更新人信息
     *
     * @param bean
     * @param loginUser
     * @return
     */
    public static void addUpdateInfo(BaseBean bean, LoginUser loginUser) {
        bean.setUpdateDt(new Date());
        bean.setUpdateId(loginUser.getId());
        bean.setUpdateName(loginUser.getUsername());
    }

    /**
     * 批量添加更新人信息
     *
     * @param beans
     * @param loginUser
     * @return
     */
    public static void addUpdateInfo(Collection<? extends BaseBean> beans, LoginUser loginUser) {
        beans.forEach(baseBean -> addUpdateInfo(baseBean, loginUser));
    }

    /**
     * 添加创建人信息
     *
     * @param baseBean
     * @param loginUser
     * @return
     */
    public static void addCreateInfo(BaseBean baseBean, LoginUser loginUser) {
        baseBean.setCreateId(loginUser.getId());
        baseBean.setCreateName(loginUser.getUsername());
        baseBean.setCreateDt(new Date());
    }

    /**
     * 批量添加创建人信息
     *
     * @param beans
     * @param loginUser
     * @return
     */
    public static void addCreateInfo(Collection<? extends BaseBean> beans, LoginUser loginUser) {
        beans.forEach(baseBean -> addCreateInfo(baseBean, loginUser));
    }
}
