package org.geekbang.ioc.overview.dependency.injection;

import org.geekbang.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;


/**
 * 依赖注入示例
 * Created by eru on 2020/7/19.
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {

        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        //BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath*:META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/dependency-injection-context.xml");

        /***
         * 1. 自定义Bean(自己用xml配置或注解配置的bean)
         * 2. 内部容器依赖的Bean(非自己定义的Bean,spring容器初始化的Bean)
         * 3.内部容器所构建的依赖(非Bean,不可通过获取依赖查找Bean的方法来获取(getBean(XXX)))
         */
        // 自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getObjectFactory().getObject());

        // 内建依赖
        userRepository.getBeanFactory();

        // 容器内建依赖
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);

        whoIsIoc(userRepository, applicationContext);
    }

    private static void whoIsIoc(UserRepository userRepository, ApplicationContext applicationContext) {
        System.out.println(userRepository.getBeanFactory() == applicationContext);
    }
}
