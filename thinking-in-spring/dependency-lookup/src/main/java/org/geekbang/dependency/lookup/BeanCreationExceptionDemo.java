package org.geekbang.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 实例
 * Created by eru on 2020/7/21.
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 BeanDefinition Bean 是一个 POJO 普通类，不过通过初始化方法 显示抛出异常
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());

        applicationContext.refresh();
        applicationContext.close();
    }

    static class POJO implements InitializingBean{

        @PostConstruct
        public void init() throws Throwable {
            throw new Throwable("【PostConstruct】 for purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("【afterPropertiesSet】for purpose...");
        }
    }
}
