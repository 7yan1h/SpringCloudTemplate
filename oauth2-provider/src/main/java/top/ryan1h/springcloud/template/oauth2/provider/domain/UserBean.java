package top.ryan1h.springcloud.template.oauth2.provider.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.ryan1h.springcloud.template.common.object.BaseBean;

/**
 * <p>
 *
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@TableName("user")
@Data
public class UserBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * bcrypt加密密码
     */
    private String password;

    /**
     * 账号状态(0:启用|1:禁用)
     */
    private Integer status;

}
