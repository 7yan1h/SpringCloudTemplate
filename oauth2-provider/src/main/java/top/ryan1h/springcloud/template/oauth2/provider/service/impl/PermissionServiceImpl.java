package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.PermissionDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.PermissionBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IPermissionService;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionBean> implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<PermissionBean> getPermissionList(String username) {
        return permissionDao.selectPermissionByUsername(username);
    }
}
