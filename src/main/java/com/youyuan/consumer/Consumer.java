package com.youyuan.consumer;

import com.youyuan.framework.Invocation;
import com.youyuan.framework.ProxyFactory;
import com.youyuan.protocol.http.HttpClient;
import com.youyuan.provider.api.HelloService;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟消费者
 * @date 2019/7/11 19:54
 */
public class Consumer {

    public static void main(String[] args) {
        //=====================
        //以下是比较简单的实现
//        String hostName="localhost";
//        Integer port=8089;
//        Invocation invocation=new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{"友缘网"});
//        String result=HttpClient.post(hostName,port,invocation);
//        System.out.println("返回结果信息result："+result);


        //=====================
        //以下通过动态代理来实现

        HelloService helloService= ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("京东"));

    }

}
