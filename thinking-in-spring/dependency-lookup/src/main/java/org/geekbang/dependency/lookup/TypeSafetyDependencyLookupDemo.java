package org.geekbang.dependency.lookup;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 安全性检查示例
 * Created by eru on 2020/7/20.
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        /// 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvaiable 方法的安全性
        displayObjectProviderIfAvailable(applicationContext);

        // 演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider Stream 操作的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
    }
    
    public static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory){
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }
    
    public static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userProvider.getIfAvailable());
    }

    public static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> userProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userProvider.getObject());
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }
    
    public static void printBeansException(String source, Runnable runnable){
        System.out.println("Source from" + source);
        System.out.println("===========================================");
        try {
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }

}
