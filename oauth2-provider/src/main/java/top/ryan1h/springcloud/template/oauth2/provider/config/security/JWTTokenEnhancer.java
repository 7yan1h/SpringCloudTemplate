package top.ryan1h.springcloud.template.oauth2.provider.config.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于给token添加自定义数据
 */
@Component
public class JWTTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        // 给token添加客户端权限属性，因为默认情况下authorities属性被用户权限属性覆盖了，貌似属性名同名
        additionalInfo.put("client_authorities", authentication.getOAuth2Request().getAuthorities());
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);

        return defaultOAuth2AccessToken;
    }
}
