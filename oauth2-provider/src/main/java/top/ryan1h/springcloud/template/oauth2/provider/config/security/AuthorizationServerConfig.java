package top.ryan1h.springcloud.template.oauth2.provider.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import top.ryan1h.springcloud.template.oauth2.provider.consts.SecurityProperties;
import top.ryan1h.springcloud.template.oauth2.provider.service.impl.ClientDetailsServiceImpl;
import top.ryan1h.springcloud.template.oauth2.provider.utils.SecurityUtils;

import java.util.Arrays;

/**
 * 授权服务器配置
 */
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端，注意这里不能注入该类，只能用new创建，否则会有堆栈溢出
        clients.withClientDetails(new ClientDetailsServiceImpl());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(
                jwtTokenEnhancer,
                accessTokenConverter()
        ));
        endpoints
                // 配置token转jwt
                .accessTokenConverter(accessTokenConverter())
                // 配置token转jwt，accessTokenConverter()也必须配置,虽然很扯
                .tokenStore(tokenStore())
                // 不配置，使用refresh_token时会报userDetailsService required
                .userDetailsService(userDetailsService)
                // 给token添加自定义数据
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置后，认证后可以访问：/oauth/check_token查看token解码后的信息，如果是jwt，也可以到https://jwt.io/查看
        security.checkTokenAccess("isAuthenticated()")
                // 配置后，资源服务器可以通过spring.oauth2.resource.jwt.key-url: /oauth/token_key获取public key。
                .tokenKeyAccess("permitAll()");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(securityProperties.getKeyPair());

        return converter;
    }

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    SecurityUtils securityUtils() {
        return new SecurityUtils();
    }

}