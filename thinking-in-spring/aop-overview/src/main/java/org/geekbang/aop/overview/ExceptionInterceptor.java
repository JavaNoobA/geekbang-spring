package org.geekbang.aop.overview;

import java.lang.reflect.Method;

/**
 * @author pengfei.zhao
 * @date 2020/12/13 15:03
 */
public interface ExceptionInterceptor {

    /**
     * @param proxy
     * @param method
     * @param args
     * @param throwable 异常信息
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
