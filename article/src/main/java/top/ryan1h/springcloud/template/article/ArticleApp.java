package top.ryan1h.springcloud.template.article;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 59941
 * @date 2019/7/10 17:29
 */
@EnableFeignClients
@SpringCloudApplication
public class ArticleApp {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApp.class, args);
    }

}


