package org.geekbang.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException} 实例
 * Created by eru on 2020/7/21.
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动 Spring 应用上下文
        ctx.refresh();
        try {
            ctx.getBean(String.class);
        }catch (NoUniqueBeanDefinitionException e){
            System.err.printf("当前 Spring 应用上下文存在%d个%s类型的 Bean, 具体原因%s\n",
                    e.getNumberOfBeansFound(), String.class, e.getMessage());
        }
        ctx.close();
    }

    @Bean
    public String bean1(){
        return "1";
    }

    @Bean
    public String bean2(){
        return "2";
    }

    @Bean
    public String bean3(){
        return "3";
    }
}
