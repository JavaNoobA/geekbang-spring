package org.geekbang.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性查找
 * Created by eru on 2020/7/20.
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 2. 设置 Parent BeanFactory
        ConfigurableListableBeanFactory parentFactory = createParentFactory();
        beanFactory.setParentBeanFactory(parentFactory);

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentFactory, "user");
        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentFactory, "user");
        // 关闭应用上下文
        applicationContext.close();

    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)){
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name ：%s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createParentFactory() {
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return listableBeanFactory;
    }
}
