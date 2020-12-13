package org.geekbang.aop.overview;

/**
 * @author pengfei.zhao
 * @date 2020/12/13 14:32
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("test");
    }
}
