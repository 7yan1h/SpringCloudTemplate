package top.ryan1h.springcloud.template.article.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ryan1h.springcloud.template.article.service.UserService;

/**
 * @author 59941
 * @date 2019/7/11 23:15
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private UserService userService;

    @GetMapping("/{name}")
    public String getArticle(@PathVariable("name") String name) {
        return userService.getAgeByName(name);
    }
}
