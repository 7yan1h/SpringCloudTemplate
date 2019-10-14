package top.ryan1h.springcloud.template.oauth2.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.ryan1h.springcloud.template.oauth2.provider.domain.RoleBean;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ryan
 * @since 2019-09-04
 */
public interface RoleDao extends BaseMapper<RoleBean> {

    List<RoleBean> selectRoleByUsername(@Param("username") String username);
}
