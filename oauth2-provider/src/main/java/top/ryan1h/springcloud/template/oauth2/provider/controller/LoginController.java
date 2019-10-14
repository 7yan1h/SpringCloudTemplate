package top.ryan1h.springcloud.template.oauth2.provider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ryan1h.springcloud.template.oauth2.provider.vo.FormLoginVO;

@RestController
@Api(tags = "登录控制器")
public class LoginController {

    @PostMapping("/login")
    @ApiOperation("表单登录")
    public void login(FormLoginVO formLoginVO) {
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public void logout() {
    }

}
