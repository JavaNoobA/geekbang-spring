package org.geekbang.dependency.lookup;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 * Created by eru on 2020/7/20.
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();

    }

    @Bean
    public String helloWorld(){ // Bean 没有指定name 默认就是方法名
        return "Hello, World";
    }

    @Bean
    @Primary
    public String message(){
        return "message";
    }

    public static void lookupIfAvailable(ApplicationContext applicationContext){
        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);
        User user = userProvider.getIfAvailable(User::createUser);
        System.out.println(user);
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        String hw = beanProvider.getObject();
        System.out.println(hw);
    }

    public static void lookupByStreamOps(ApplicationContext applicationContext){
        ObjectProvider<String> stringProvider = applicationContext.getBeanProvider(String.class);
        stringProvider.stream().forEach(System.out::println);
    }
}
