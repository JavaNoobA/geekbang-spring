package org.geekbang.aop.overview;

import java.lang.reflect.Method;

/**
 * 后置拦截器
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:55
 */
public interface AfterInterceptor {

    /**
     * 后置拦截
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object after(Object proxy, Method method, Object[] args);
}
