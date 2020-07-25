package org.geekbang.bean.scope;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Map;

/**
 * Bean 作用域 Demo
 * Created by eru on 2020/7/23.
 */
public class BeanScopeDemo {

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableBeanFactory beanFactory;

    @Bean
    // 默认scope(作用域)是单例 singleton
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称: %s 正在初始化回调..\n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 结论一:
        // Singleton Bean 无论依赖注入还是依赖查找，均为同一个对象
        // Prototype Bean 无论依赖注入还是依赖查找，均为新生成的对象

        // 结论二：
        // 如果依赖注入为集合对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入

        // 结论三：
        // 无论是 Singleton Bean 还是 Prototype Bean 均会执行初始化方法
        // 不过仅 Singleton Bean 会执行销毁方法

        scopedBeanByLookup(applicationContext);
        scopedBeanByInjection(applicationContext);

        applicationContext.close();
    }

    public static void scopedBeanByLookup(AnnotationConfigApplicationContext applicationContext){
        for (int i = 0; i < 3; i++) {
            // 单例的 Bean 全局共享
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("【singletonUser】=" + singletonUser);
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("【prototypeUser】=" + prototypeUser);
        }
    }

    public static void scopedBeanByInjection(AbstractApplicationContext applicationContext){
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("【singletonUser】 = " + demo.singletonUser);
        System.out.println("【singletonUser1】 = " + demo.singletonUser1);
        System.out.println("【prototypeUser】 = " + demo.prototypeUser);
        System.out.println("【prototypeUser1】 = " + demo.prototypeUser1);
        System.out.println("【users】 = " + demo.users);
    }
}
