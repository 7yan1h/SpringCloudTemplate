package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import top.ryan1h.springcloud.template.oauth2.provider.dao.Oauth2ClientDetailsDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.Oauth2ClientDetailsBean;

import java.util.Arrays;

/**
 * 验证传入的客户端id是否在数据库存在，并加载客户端详情
 */
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private Oauth2ClientDetailsDao oauth2ClientDetailsDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        LambdaQueryWrapper<Oauth2ClientDetailsBean> select = new LambdaQueryWrapper<Oauth2ClientDetailsBean>().eq(Oauth2ClientDetailsBean::getClientId, clientId);
        Oauth2ClientDetailsBean oauth2ClientDetailsBean = oauth2ClientDetailsDao.selectOne(select);
        if (null != oauth2ClientDetailsBean) {
            BaseClientDetails clientDetails = new BaseClientDetails(
                    clientId,
                    // 暂时没发现它的作用
                    oauth2ClientDetailsBean.getResourceIds(),
                    // 可以当作客户端的authorities属性使用，资源服务器可以使用@PreAuthorize("#oauth2.hasScope('web')")
                    oauth2ClientDetailsBean.getScope(),
                    // 授权类型
                    oauth2ClientDetailsBean.getAuthorizedGrantTypes(),
                    // 暂时没发现它的作用，且这个属性会在生成token时，被用户信息的authorities属性值覆盖，
                    oauth2ClientDetailsBean.getAuthorities(),
                    // 用户同意授权后，跳转的url，如果是授权码授权，url会是：https://www.baidu.com/?code=3Onqer&state=1234
                    oauth2ClientDetailsBean.getWebServerRedirectUri());

            // 必须配置客户端密码,因为获取token时需要
            clientDetails.setClientSecret(oauth2ClientDetailsBean.getClientSecret());
            // 设置token有效期
            clientDetails.setAccessTokenValiditySeconds(oauth2ClientDetailsBean.getAccessTokenValidity());
            // 设置refresh_token有效期
            clientDetails.setRefreshTokenValiditySeconds(oauth2ClientDetailsBean.getRefreshTokenValidity());

            // 自动授权,前端应在请求授权服务器授权之前，先确认用户是否同意该scope授权，同意则直接调用授权接口授权
            String scopeString = oauth2ClientDetailsBean.getScope();
            if (StringUtils.isNotEmpty(scopeString)) {
                String[] scopes = scopeString.split(",");
                if (scopes != null && scopes.length > 0) {
                    clientDetails.setAutoApproveScopes(Arrays.asList(scopes));
                }
            }

            return clientDetails;
        } else {
            throw new NoSuchClientException("客户端id: " + clientId + " 不存在");
        }
    }
}
