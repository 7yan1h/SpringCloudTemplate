package top.ryan1h.springcloud.template.user.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 59941
 * @date 2019/7/11 23:12
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/{name}")
    public String getAgeByName(@PathVariable("name") String name) {
        log.debug("debug");
        log.info("info");
        return name + "," + this.port;
    }
}
