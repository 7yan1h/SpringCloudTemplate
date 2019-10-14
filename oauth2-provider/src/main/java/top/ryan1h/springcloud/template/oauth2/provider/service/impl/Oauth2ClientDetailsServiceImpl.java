package top.ryan1h.springcloud.template.oauth2.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.oauth2.provider.dao.Oauth2ClientDetailsDao;
import top.ryan1h.springcloud.template.oauth2.provider.domain.Oauth2ClientDetailsBean;
import top.ryan1h.springcloud.template.oauth2.provider.service.IOauth2ClientDetailsService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ryan1h
 * @since 2019-10-13
 */
@Service
public class Oauth2ClientDetailsServiceImpl extends ServiceImpl<Oauth2ClientDetailsDao, Oauth2ClientDetailsBean> implements IOauth2ClientDetailsService {

}
