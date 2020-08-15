package com.eru.threadandjuc;

import java.util.concurrent.TimeUnit;

/**
 * @author pengfei.zhao
 * @date 2020/8/15 15:26
 */
public class SleepUtils {
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
