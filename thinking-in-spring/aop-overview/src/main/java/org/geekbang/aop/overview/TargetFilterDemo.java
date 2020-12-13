package org.geekbang.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * AOP 目标过滤器示例
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:20
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String clazz = "org.geekbang.aop.overview.EchoService";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> desClazz = classLoader.loadClass(clazz);
        Method desMethod = ReflectionUtils.findMethod(desClazz, "echo", String.class);
        System.out.println(desMethod);

        ReflectionUtils.doWithMethods(desClazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerException的方法" + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Class[] parameterTypes = method.getParameterTypes();
                Class[] exceptionTypes = method.getExceptionTypes();
                return parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0].getClass())
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });
    }
}
