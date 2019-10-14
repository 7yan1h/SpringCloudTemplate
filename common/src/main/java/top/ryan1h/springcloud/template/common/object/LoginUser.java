package top.ryan1h.springcloud.template.common.object;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户
 */
@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = -4715360944617885545L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 数据库中的密码
     */
    private String password;

    /**
     * 用户的权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUser(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public LoginUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public LoginUser(Long id, String username, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 这些重写方法的返回值关系到rememberMe的验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
