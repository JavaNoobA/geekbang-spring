package org.geekbang.spring.bean.definition;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 * Created by eru on 2020/7/19.
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User user = beanFactory.getBean("user", User.class);
        User springUser = beanFactory.getBean("spring-user", User.class);

        System.out.println("user Bean 是否和 alias springUser bean 相同: " + (user == springUser));
    }
}
