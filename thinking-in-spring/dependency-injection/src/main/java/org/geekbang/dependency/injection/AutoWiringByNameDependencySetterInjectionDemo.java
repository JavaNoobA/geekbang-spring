package org.geekbang.dependency.injection;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Setter 示例
 * Created by eru on 2020/7/21.
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-injection-setter.xml";
        reader.loadBeanDefinitions(location);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        User user = userHolder.getUser();
        System.out.println(user);
    }
}
