package top.ryan1h.springcloud.template.oauth2.provider.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.ryan1h.springcloud.template.common.object.BaseBean;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ryan1h
 * @since 2019-10-13
 */
@Data
@TableName("oauth2_client_details")
public class Oauth2ClientDetailsBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 资源id，使用,分割
     */
    private String resourceIds;

    /**
     * 客户端密码
     */
    private String clientSecret;

    /**
     * 作用域，使用,分割
     */
    private String scope;

    /**
     * 授权类型，使用,分割
     */
    private String authorizedGrantTypes;

    /**
     * 授权后，重定向url
     */
    private String webServerRedirectUri;

    /**
     * 客户端权限，使用,分割
     */
    private String authorities;

    /**
     * access token有效期
     */
    private Integer accessTokenValidity;

    /**
     * refresh token有效期
     */
    private Integer refreshTokenValidity;

    /**
     * 额外信息
     */
    private String additionalInformation;

    /**
     * 自动同意授权的scope，使用,分割
     */
    private String autoapprove;


}
