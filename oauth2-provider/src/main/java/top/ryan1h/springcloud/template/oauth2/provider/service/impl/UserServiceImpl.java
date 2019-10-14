package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.UserDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.UserBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ryan
 * @since 2019-09-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserBean> implements IUserService {

}
