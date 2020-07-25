package org.geekbang.bean.scope.web;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring Web MVC 配置
 * Created by eru on 2020/7/25.
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("飞机");
        return user;
    }
}
