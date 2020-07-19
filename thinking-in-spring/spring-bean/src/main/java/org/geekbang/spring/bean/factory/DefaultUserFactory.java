package org.geekbang.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by eru on 2020/7/19.
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 1. PostConstruct
    @PostConstruct
    public void init(){
        System.out.println("【PostConstruct】 Bean init...");
    }

    // 3. initUserFactory
    public void initUserFactory(){
        System.out.println("【initUserFactory】invoking...");
    }

    // 2. afterPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【afterPropertiesSet】invoking");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("[preDestroy] bean 销毁中 ");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("[destroy]");
    }

    public void doDestroy(){
        System.out.println("[doDestroy]");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前 DefaultUserFactory 对象正在被垃圾回收。。。");
    }
}
