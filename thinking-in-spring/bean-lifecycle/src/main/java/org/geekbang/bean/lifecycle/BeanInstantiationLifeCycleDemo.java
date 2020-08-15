package org.geekbang.bean.lifecycle;

import org.geekbang.ioc.overview.domain.SuperUser;
import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Bean 初始化 Demo
 * @author pengfei.zhao
 * @date 2020/7/26 9:05
 */
public class BeanInstantiationLifeCycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationBeanPostProcessor());
        // 示例化 Properties 资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";

        int readBeanCount = reader.loadBeanDefinitions(location);
        System.out.println("已加载bean数量：" + readBeanCount);

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
        SuperUser superUser = beanFactory.getBean(SuperUser.class);
        System.out.println(superUser);
        List.of()
    }

    static class MyInstantiationBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)){
                return new SuperUser();
            }
            return null; // 如果是 null, 则不处理返回默认的 Bean
        }
    }
}
