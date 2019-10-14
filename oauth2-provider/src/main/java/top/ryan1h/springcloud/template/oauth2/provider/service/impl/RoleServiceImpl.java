package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.RoleDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.RoleBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IRoleService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleBean> implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleBean> getRoleList(String username) {
        return roleDao.selectRoleByUsername(username);
    }
}
