package org.geekbang.dependency.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by eru on 2020/7/24.
 */
@PropertySource(value = "META-INF/user.properties", encoding = "UTF-8")
@Configuration
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;
    @Value("${usr.name}")
    private String name;

    @Value("${user.path}:defaultPath")
    private String path;

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id=" + demo.id);
        System.out.println("demo.name=" + demo.name);
        System.out.println("demo.path=" + demo.path);

        applicationContext.close();
    }
}
