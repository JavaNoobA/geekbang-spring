package org.geekbang.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @author pengfei.zhao
 * @date 2020/7/25 19:39
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于注解 AnnotatedBeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionBeforeCount = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionAfterCount = beanFactory.getBeanDefinitionCount();

        System.out.println("count=" + (beanDefinitionAfterCount - beanDefinitionBeforeCount));
    }
}
