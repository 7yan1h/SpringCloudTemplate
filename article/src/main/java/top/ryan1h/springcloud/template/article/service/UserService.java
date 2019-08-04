package top.ryan1h.springcloud.template.article.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.ryan1h.springcloud.template.article.service.impl.UserServiceImpl;

/**
 * @author 59941
 * @date 2019/7/11 19:50
 */
// 用fallback指定降级处理的实现类
@FeignClient(value = "user-service", fallback = UserServiceImpl.class)
public interface UserService {

    @GetMapping("/users/{name}")
    String getAgeByName(@PathVariable("name") String name);

}
