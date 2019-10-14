package top.ryan1h.springcloud.template.oauth2.provider.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;
import top.ryan1h.springcloud.template.common.object.LoginUser;
import top.ryan1h.springcloud.template.oauth2.provider.utils.SecurityUtils;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        LoginUser dbUser = (LoginUser) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, dbUser.getPassword())) {
            throw new BadCredentialsException(JSON.toJSONString(MsgEnum.E00010));
        }

        List<GrantedAuthority> authorityList = securityUtils.getUserAuthorities(username);

        // 注册该session,否则sessionRegistry获取不到登录了的用户,也会导致session无法管理
        sessionRegistry.registerNewSession(
                RequestContextHolder.currentRequestAttributes().getSessionId(),
                username);

        return new FormLoginAuthenticationToken(
                dbUser.getId(),
                dbUser.getUsername(),
                null,
                authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
