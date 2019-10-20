package top.ryan1h.springcloud.template.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 59941
 * @date 2019/7/18 23:25
 */
@EnableZuulProxy
@SpringBootApplication
@EnableOAuth2Sso
public class ZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class, args);
    }
}
