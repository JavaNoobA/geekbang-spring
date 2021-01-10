package org.geekbang.spring.bean.definition;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * Created by eru on 2020/7/19.
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 1. 通过 BeanDefinitionBuilder
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1);
        builder.addPropertyValue("name", "erudev");
        BeanDefinition bd = builder.getBeanDefinition();
        System.out.println(bd);

        // 2. 通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition gbf = new GenericBeanDefinition();
        MutablePropertyValues properties = new MutablePropertyValues();
        properties.addPropertyValue("id", 1);
        properties.addPropertyValue("name", "erudev");
        gbf.setPropertyValues(properties);
        System.out.println(gbf);
    }
}
