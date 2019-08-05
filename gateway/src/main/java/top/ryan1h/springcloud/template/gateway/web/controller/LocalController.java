package top.ryan1h.springcloud.template.gateway.web.controller;

import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author 59941
 * @date 2019/7/21 17:11
 */
@RestController
@RequestMapping("/gateway")
@SpringCloudApplication
public class LocalController {

    Environment environment;

    @GetMapping("/local")
    public String local() {
        Arrays.stream(environment.getActiveProfiles()).forEach(System.out::println);

        Arrays.stream(environment.getDefaultProfiles()).forEach(System.out::println);

        return "local";
    }
}

