package top.ryan1h.springcloud.template.oauth2.provider.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import top.ryan1h.sb.template.common.object.BaseBean;

/**
 * <p>
 *
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@TableName("role_permission")
public class RolePermissionBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 角色表id
     */
    private Long roleId;

    /**
     * 权限表id
     */
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionBean{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                "}";
    }
}
