package org.geekbang.aop.overview;

/**
 * @author pengfei.zhao
 * @date 2020/12/13 14:33
 */
public class DefaultEchoService implements EchoService{
    @Override
    public String echo(String message) throws NullPointerException {
        return "[Echo] " + message;
    }
}
