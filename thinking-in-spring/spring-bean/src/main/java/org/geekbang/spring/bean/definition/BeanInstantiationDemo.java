package org.geekbang.spring.bean.definition;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 * Created by eru on 2020/7/19.
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstance = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactory = beanFactory.getBean("userFactory", User.class);
        System.out.println(user);
        System.out.println(userByInstance);
        System.out.println(userByFactory);

        System.out.println(user == userByInstance);
        System.out.println(userByInstance == userByFactory);
    }
}
