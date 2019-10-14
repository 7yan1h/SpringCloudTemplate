package top.ryan1h.springcloud.template.oauth2.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.ryan1h.springcloud.template.oauth2.provider.domain.PermissionBean;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
public interface PermissionDao extends BaseMapper<PermissionBean> {

    List<PermissionBean> selectPermissionByUsername(@Param("username") String username);
}
