package org.geekbang.spring.bean.definition;

import org.geekbang.spring.bean.factory.DefaultUserFactory;
import org.geekbang.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by eru on 2020/7/19.
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(BeanInitializationDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
