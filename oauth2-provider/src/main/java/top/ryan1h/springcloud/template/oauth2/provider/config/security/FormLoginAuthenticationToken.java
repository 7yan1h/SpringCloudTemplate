package top.ryan1h.springcloud.template.oauth2.provider.config.security;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class FormLoginAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -989217507784529330L;

    /**
     * 登录用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String principal;

    /**
     * 密码
     */
    private String credentials;

    public FormLoginAuthenticationToken(Long id, String principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.principal = principal;
        this.credentials = credentials;
        // 很重要,否则登录了也没有用
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
