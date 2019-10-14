package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.UserRoleDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.UserRoleBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IUserRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleBean> implements IUserRoleService {

}
