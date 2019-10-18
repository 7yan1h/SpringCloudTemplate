package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import top.ryan1h.sb.template.common.enums.MsgEnum;
import top.ryan1h.sb.template.common.enums.UserStatusEnum;
import top.ryan1h.sb.template.common.object.LoginUser;
import top.ryan1h.springcloud.template.oauth2.provider.domain.UserBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IUserService;
import top.ryan1h.springcloud.template.oauth2.provider.utils.SecurityUtils;

import java.util.List;

/**
 * 验证传入的用户名是否在数据库存在，并加载用户详情
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Wrapper<UserBean> wrapper = Wrappers.<UserBean>lambdaQuery()
                .eq(UserBean::getUsername, username);
        UserBean user = userService.getOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException(JSON.toJSONString(MsgEnum.E00005));
        }

        if (user.getStatus().equals(UserStatusEnum.BAN.getStatus())) {
            throw new LockedException(JSON.toJSONString(MsgEnum.E00007));
        }

        List<GrantedAuthority> authorityList = securityUtils.getUserAuthorities(username);

        // 注册该session,否则sessionRegistry获取不到登录了的用户,也会导致session无法管理
        sessionRegistry.registerNewSession(
                RequestContextHolder.currentRequestAttributes().getSessionId(),
                username);

        return new LoginUser(user.getId(), user.getUsername(), user.getPassword(), authorityList);
    }

}