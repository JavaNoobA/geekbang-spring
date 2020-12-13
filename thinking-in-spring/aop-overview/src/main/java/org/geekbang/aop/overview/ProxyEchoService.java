package org.geekbang.aop.overview;

/**
 * @author pengfei.zhao
 * @date 2020/12/13 14:30
 */
public class ProxyEchoService implements EchoService {
    private EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) throws NullPointerException {
        long start = System.currentTimeMillis();
        String result = echoService.echo(message);
        long costTime = System.currentTimeMillis() - start;
        System.out.println("echo 方法执行消耗的时间: " + costTime);
        return result;
    }
}
