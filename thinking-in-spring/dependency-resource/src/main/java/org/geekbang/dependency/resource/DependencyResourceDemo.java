package org.geekbang.dependency.resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * Spring 三种依赖注入来源
 * 1. 自定义注册的 Spring Bean (通过 XML、注解或者 API 注册的 BeanDefinition 创建)
 * 2. 内建的 Spring Bean (通过 registerSingleton)
 * 3. 以及内建的可注入的依赖 （通过 {@link AbstractApplicationContext#registerResolvableDependency}）
 * Created by eru on 2020/7/23.
 */
public class DependencyResourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initByInjection(){
        System.out.println("beanFactory == resource " + (beanFactory == resourceLoader));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("ApplicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup(){
        try {
            getBean(BeanFactory.class);
            getBean(ResourceLoader.class);
            getBean(ApplicationEventPublisher.class);
            getBean(ApplicationContext.class);
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("BeanFactory 找不到" + e.getBeanName());
        }
    }

    public <T> T getBean(Class<T> beanType){
        try {
            return beanFactory.getBean(beanType);
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("当前类型" + beanType.getName() + "无法在 BeanFactory 中查找到!");
        }
        return null;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(DependencyResourceDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }
}
