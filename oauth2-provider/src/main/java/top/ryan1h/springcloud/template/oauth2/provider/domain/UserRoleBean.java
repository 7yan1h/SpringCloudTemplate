package top.ryan1h.springcloud.template.oauth2.provider.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import top.ryan1h.springcloud.template.common.object.BaseBean;

/**
 * <p>
 *
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@TableName("user_role")
public class UserRoleBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    private Long userId;

    /**
     * 角色表id
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleBean{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}
