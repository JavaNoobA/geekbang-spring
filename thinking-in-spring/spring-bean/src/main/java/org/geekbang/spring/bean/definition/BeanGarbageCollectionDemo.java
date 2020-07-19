package org.geekbang.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收示例
 * Created by eru on 2020/7/20.
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(BeanInitializationDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        applicationContext.close();
        
        Thread.sleep(3000);
        System.gc();
        Thread.sleep(3000);

    }
}
