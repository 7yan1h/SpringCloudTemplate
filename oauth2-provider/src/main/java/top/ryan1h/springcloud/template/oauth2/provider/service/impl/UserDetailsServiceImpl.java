package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.ryan1h.springcloud.template.common.enums.MsgEnum;
import top.ryan1h.springcloud.template.common.enums.UserStatusEnum;
import top.ryan1h.springcloud.template.common.object.LoginUser;
import top.ryan1h.springcloud.template.oauth2.provider.domain.UserBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IUserService;

/**
 * 验证传入的用户名是否在数据库存在，并加载用户详情
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

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

        return new LoginUser(user.getId(), user.getUsername(), user.getPassword());
    }

}