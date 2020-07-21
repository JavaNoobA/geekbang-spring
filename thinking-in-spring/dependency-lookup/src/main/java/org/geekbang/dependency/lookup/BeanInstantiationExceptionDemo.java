package org.geekbang.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link} BeanInstantiationException 实例
 * Created by eru on 2020/7/21.
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanDefinition Bean 是一个 CharSequence 接口
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());

        applicationContext.refresh();
        applicationContext.close();
    }
}
