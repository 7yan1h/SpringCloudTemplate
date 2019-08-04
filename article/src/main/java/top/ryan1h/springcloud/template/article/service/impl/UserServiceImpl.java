package top.ryan1h.springcloud.template.article.service.impl;

import org.springframework.stereotype.Service;
import top.ryan1h.springcloud.template.article.service.UserService;

/**
 * @author 59941
 * @date 2019/7/15 0:44
 */
@Service
public class UserServiceImpl implements UserService {

    // 实现降级处理方法
    @Override
    public String getAgeByName(String name) {
        return "User服务调用失败";
    }

}
