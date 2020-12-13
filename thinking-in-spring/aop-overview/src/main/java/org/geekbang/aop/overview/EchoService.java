package org.geekbang.aop.overview;

/**
 * Echo 服务
 *
 * @author pengfei.zhao
 * @date 2020/12/13 14:16
 */
public interface EchoService {
    String echo(String message) throws NullPointerException;
}
