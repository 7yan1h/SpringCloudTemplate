package top.ryan1h.springcloud.template;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ryan1h.sb.template.common.consts.security.Authority;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    @GetMapping("/user")
    // 根据token中的数据，可以使用@PreAuthorize注解进行权限控制
    @PreAuthorize("hasAuthority('" + Authority.PERMISSION_USER_ADD + "')" +
            "|| #oauth2.hasScope('web1')")
    public Map<String, Object> user(OAuth2Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", authentication.getPrincipal());
        userInfo.put("authorities", authentication.getAuthorities());

        return userInfo;
    }


}
