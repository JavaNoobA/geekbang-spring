package org.geekbang.aop.overview;

import java.lang.reflect.Method;

/**
 * 前置拦截器
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:55
 */
public interface BeforeInterceptor {

    /**
     * 前置拦截
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
