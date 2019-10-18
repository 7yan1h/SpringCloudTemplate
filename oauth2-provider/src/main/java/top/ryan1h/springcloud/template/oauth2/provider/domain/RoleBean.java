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
@TableName("role")
public class RoleBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 角色码
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色描述
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
        return "RoleBean{" +
                "code=" + code +
                ", name=" + name +
                ", description=" + description +
                "}";
    }
}
