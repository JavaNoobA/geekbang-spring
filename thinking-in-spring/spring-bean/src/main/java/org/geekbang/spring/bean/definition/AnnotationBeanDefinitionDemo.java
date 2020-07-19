package org.geekbang.spring.bean.definition;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by eru on 2020/7/19.
 */
// 3. @Import 导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 通过 BeanDefinition 注册
        // 1. 命名 Bean 的方式注册
        // 2. 非命名方式 注册
        registryBeanDefinitionWithBeanName(applicationContext, "erudev-user");
        registryBeanDefinitionWithBeanName(applicationContext);

        System.out.println("User 类型的 Bean：" + applicationContext.getBeansOfType(User.class));
        System.out.println("Config 类型的 Bean：" + applicationContext.getBeansOfType(User.class));

        // 关闭应用上下文
        applicationContext.close();
    }

    public static void registryBeanDefinitionWithBeanName(BeanDefinitionRegistry registry, String beanName){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1L)
                .addPropertyValue("name", "spring");

        if (StringUtils.hasText(beanName)){
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    public static void registryBeanDefinitionWithBeanName(BeanDefinitionRegistry registry){
        registryBeanDefinitionWithBeanName(registry, null);
    }

    @Component
    static class Config{

        @Bean(name = {"user", "spring-user"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("spring");
            return user;
        }
    }
}
