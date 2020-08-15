package com.eru.threadandjuc;

/**
 * @author pengfei.zhao
 * @date 2020/8/15 15:52
 */
public class Synchronized {

    public static void main(String[] args) {
        synchronized (Synchronized.class){

        }

        m();
    }

    private static synchronized void m() {

    }
}
