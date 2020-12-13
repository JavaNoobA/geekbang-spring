package org.geekbang.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP 拦截器示例
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:56
 */
public class AopInterceptorDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };
                    Long startTime = 0L;
                    Long endTime = 0L;
                    Object result = null;
                    try {
                        // 前置拦截, 会执行上面前置拦截器的before方法
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        result = echoService.echo((String) args[0]);
                        AfterInterceptor afterInterceptor = new AfterInterceptor() {
                            @Override
                            public Object after(Object proxy, Method method, Object[] args) {
                                return System.currentTimeMillis();
                            }
                        };
                        endTime = (Long) afterInterceptor.after(proxy, method, args);
                        // 后置拦截
                    } catch (Exception e) {
                        // 异常拦截器
                        ExceptionInterceptor interceptor = (proxy1, method1, args1, throwable) -> {
                        };
                    } finally {
                        // finally 后置拦截器
                        FinallyInterceptor interceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Long costTime = (Long) interceptor.finalize(proxy, method, args, result);
                        System.out.println("echo 方法执行的实现：" + costTime + " ms.");
                    }
                }
                return null;
            }
        });
    }
}
class TimeFinallyInterceptor implements FinallyInterceptor{
    private final Long startTime;

    private final Long endTime;

    TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Object finalize(Object proxy, Method method, Object[] args, Object returnResult) {
        // 方法执行时间（毫秒）
        Long costTime = endTime - startTime;
        return costTime;
    }
}