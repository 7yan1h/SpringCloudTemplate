package top.ryan1h.springcloud.template.oauth2.provider.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.util.CollectionUtils;
import top.ryan1h.springcloud.template.common.object.LoginUser;
import top.ryan1h.springcloud.template.common.security.Authority;
import top.ryan1h.springcloud.template.oauth2.provider.config.security.FormLoginAuthenticationToken;
import top.ryan1h.springcloud.template.oauth2.provider.domain.PermissionBean;
import top.ryan1h.springcloud.template.oauth2.provider.domain.RoleBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IPermissionService;
import top.ryan1h.springcloud.template.oauth2.provider.service.IRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SecurityUtils {
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private SessionRepository sessionRepository;

    /**
     * 刷新登录的用户的信息,比如数据库更新用户权限后,需要调用该方法刷新服务器中的认证信息,否则权限信息不会同步
     *
     * @param username
     */
    public void refreshAuthentication(String username) {
        List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(username, false);
        if (!CollectionUtils.isEmpty(sessionInformationList)) {
            for (SessionInformation sessionInformation : sessionInformationList) {
                Authentication newAuthentication = createNewAuthentication(username);
                if (newAuthentication == null) {
                    continue;
                }

                Session session = sessionRepository.findById(sessionInformation.getSessionId());
                if (session == null) {
                    continue;
                }
                SecurityContext securityContext = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
                securityContext.setAuthentication(newAuthentication);

                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
                sessionRepository.save(session);
            }
        }
    }

    /**
     * 创建一个最新的Authentication对象
     *
     * @param username
     * @return
     */
    private Authentication createNewAuthentication(String username) {
        LoginUser dbUser = (LoginUser) userDetailsService.loadUserByUsername(username);
        if (dbUser != null) {
            List<GrantedAuthority> authorityList = getUserAuthorities(username);

            return new FormLoginAuthenticationToken(
                    dbUser.getId(),
                    username,
                    null,
                    authorityList);
        }

        return null;
    }


    public List<GrantedAuthority> getUserAuthorities(String username) {
        List<PermissionBean> permissionBeanList = Optional.ofNullable(permissionService.getPermissionList(username))
                .orElse(new ArrayList<>());

        List<RoleBean> roleBeanList = Optional.ofNullable(roleService.getRoleList(username))
                .orElse(new ArrayList<>());

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (PermissionBean permissionBean : permissionBeanList) {
            authorityList.add(new SimpleGrantedAuthority(Authority.AuthorityPrefix.PERMISSION + permissionBean.getCode()));
        }

        for (RoleBean roleBean : roleBeanList) {
            authorityList.add(new SimpleGrantedAuthority(Authority.AuthorityPrefix.ROLE + roleBean.getCode()));
        }

        return authorityList;
    }
}
