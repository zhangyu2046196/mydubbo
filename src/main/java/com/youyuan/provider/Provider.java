package com.youyuan.provider;

import com.youyuan.framework.URL;
import com.youyuan.protocol.http.HttpServer;
import com.youyuan.provider.api.HelloService;
import com.youyuan.provider.impl.HelloServiceImpl;
import com.youyuan.register.RemoteMapRegist;

/**
 * @author zhangyu
 * @version 1.0
 * @description 服务提供者启动类
 * @date 2019/7/11 18:31
 */
public class Provider {

    private static String hostName="localhost";
    private static Integer port=8089;

    public static void main(String[] args) {

        //远程注册
        RemoteMapRegist.regist(HelloService.class.getName(),new URL(hostName,port));

        //本地注册
        //存储接口和实现类
        LocalMapRegist.regist(HelloService.class.getName(), HelloServiceImpl.class);

        //启动tomcat
        HttpServer httpServer=new HttpServer();
        httpServer.start(hostName,port);
    }

}
