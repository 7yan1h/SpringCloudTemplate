package top.ryan1h.springcloud.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SsoClientApp {

    public static void main(String[] args) {
        SpringApplication.run(SsoClientApp.class, args);
    }
}
