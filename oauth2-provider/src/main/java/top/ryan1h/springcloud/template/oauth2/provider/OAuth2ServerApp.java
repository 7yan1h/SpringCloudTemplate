package top.ryan1h.springcloud.template.oauth2.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@MapperScan("top.ryan1h.springcloud.template.oauth2.provider.dao")
public class OAuth2ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApp.class, args);
    }
}
