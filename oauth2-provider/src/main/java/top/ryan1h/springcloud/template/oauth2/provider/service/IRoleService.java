package top.ryan1h.springcloud.template.oauth2.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ryan1h.springcloud.template.oauth2.provider.domain.RoleBean;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
public interface IRoleService extends IService<RoleBean> {

    List<RoleBean> getRoleList(String username);
}
