package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.RolePermissionDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.RolePermissionBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IRolePermissionService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionBean> implements IRolePermissionService {

}
