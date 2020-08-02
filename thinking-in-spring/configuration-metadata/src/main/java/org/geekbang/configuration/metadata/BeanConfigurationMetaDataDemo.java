package org.geekbang.configuration.metadata;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息 Demo
 * @author pengfei.zhao
 * @date 2020/8/2 14:20
 */
public class BeanConfigurationMetaDataDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "zpf");

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // setAttribute 不会影响 Bean
        beanDefinition.setAttribute("name", "张三");
        // 当前 Bean 来自哪里(辅助作用)
        beanDefinition.setSource(BeanConfigurationMetaDataDemo.class);

        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("user", beanDefinition);
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())){
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetaDataDemo.class.equals(bd.getSource())){
                        String name = (String) bd.getAttribute("name");
                        User user = (User)bean;
                        user.setName(name);
                    }
                }
                return null;
            }
        });

        User user = beanFactory.getBean(User.class);

        System.out.println(user);
    }
}
