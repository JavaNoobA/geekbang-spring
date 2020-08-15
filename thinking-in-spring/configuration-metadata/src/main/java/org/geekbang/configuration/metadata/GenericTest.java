package org.geekbang.configuration.metadata;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengfei.zhao
 * @date 2020/8/5 7:47
 */
public class GenericTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.getClass().getMethod("add", Object.class).invoke(list, "add");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
