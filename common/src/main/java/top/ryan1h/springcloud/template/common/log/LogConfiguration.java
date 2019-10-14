package top.ryan1h.springcloud.template.common.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfiguration {

    @Bean
    public LogAop logAop() {
        return new LogAop();
    }
}
