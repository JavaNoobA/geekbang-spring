package org.geekbang.aop.overview;


/**
 * 类加载
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:09
 */
public class ClassLoadingDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader;
        while (true) {
            parentClassLoader = parentClassLoader.getParent();
            if (parentClassLoader == null) { // Boostrap classloader
                break;
            }
            System.out.println(parentClassLoader);
        }
    }
}
