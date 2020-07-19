package org.geekbang.spring.bean.definition;

import org.geekbang.spring.bean.factory.DefaultUserFactory;
import org.geekbang.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by eru on 2020/7/20.
 */
public class SingletonRegistrationBeanDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 启动应用上下文
        applicationContext.refresh();
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册
        beanFactory.registerSingleton("userFactory", userFactory);

        UserFactory userLookUpFactory = applicationContext.getBean(UserFactory.class);

        applicationContext.close();

        System.out.println("userFactory == userLookUpFactory :" + (userFactory == userLookUpFactory));
    }
}
