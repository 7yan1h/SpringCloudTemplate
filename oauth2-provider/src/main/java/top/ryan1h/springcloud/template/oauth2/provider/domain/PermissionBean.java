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
@TableName("permission")
public class PermissionBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 权限码
     */
    private String code;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限描述
     */
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PermissionBean{" +
                "code=" + code +
                ", name=" + name +
                ", description=" + description +
                "}";
    }
}
