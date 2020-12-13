package org.geekbang.aop.overview;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author pengfei.zhao
 * @date 2020/12/13 14:37
 */
public class JDKDynamicProxyDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService proxyEchoService = new ProxyEchoService(new DefaultEchoService());
                    return proxyEchoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService)proxy;
        echoService.echo("hello, world");
    }
}
