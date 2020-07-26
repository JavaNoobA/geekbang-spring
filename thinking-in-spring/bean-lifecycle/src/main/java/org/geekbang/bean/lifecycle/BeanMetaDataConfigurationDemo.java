package org.geekbang.bean.lifecycle;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 * Created by eru on 2020/7/25.
 */
public class BeanMetaDataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 示例化 Properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "/META-INF/user.properties";
        ClassPathResource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int readBeanCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载bean数量：" + readBeanCount);
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

}
