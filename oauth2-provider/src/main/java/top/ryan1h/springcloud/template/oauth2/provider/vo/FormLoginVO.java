package top.ryan1h.springcloud.template.oauth2.provider.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("表单登录VO")
public class FormLoginVO implements Serializable {
    private static final long serialVersionUID = -1674334017208356530L;

    @ApiModelProperty(value = "用户名", example = "hx")
    private String username;

    @ApiModelProperty(value = "密码", example = "1")
    private String password;

}
