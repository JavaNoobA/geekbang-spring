package org.geekbang.aop.overview;

import java.lang.reflect.Method;

/**
 * 最终执行后置拦截器
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:52
 */
public interface FinallyInterceptor {

    /**
     * 最终执行
     * @param proxy
     * @param method
     * @param args
     * @param returnResult
     * @return
     */
    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
