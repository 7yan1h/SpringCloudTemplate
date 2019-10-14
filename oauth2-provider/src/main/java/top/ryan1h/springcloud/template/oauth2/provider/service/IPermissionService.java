package top.ryan1h.springcloud.template.oauth2.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ryan1h.springcloud.template.oauth2.provider.domain.PermissionBean;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
public interface IPermissionService extends IService<PermissionBean> {

    List<PermissionBean> getPermissionList(String username);

}
