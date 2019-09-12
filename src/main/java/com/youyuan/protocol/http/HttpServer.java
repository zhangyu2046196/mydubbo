package com.youyuan.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @author zhangyu
 * @version 1.0
 * @description 内嵌tomcat
 * @date 2019/7/11 17:23
 */
public class HttpServer {

    /**
     * tomcat启动
     * @param hostName 地址
     * @param port 端口号
     */
    public void start(String hostName,Integer port){

        //============================
        //以下基础信息赋值
        //创建tomcat
        Tomcat tomcat=new Tomcat();

        //创建server
        Server server=tomcat.getServer();

        //创建service
        Service service=tomcat.getService();

        //创建connector
        Connector connector=new Connector();
        connector.setPort(port);

        //创建engine
        Engine engine=new StandardEngine();
        engine.setDefaultHost(hostName);

        //创建host
        Host host=new StandardHost();
        host.setName(hostName);

        //创建context
        String contextPath="";
        Context context=new StandardContext();
        context.setPath(contextPath);
        //添加监听器，tomcat才能够启动成功
        context.addLifecycleListener(new Tomcat.FixContextListener());

        //=======================================
        //以下代码拼装tomcat的层次结构

        host.addChild(context);

        engine.addChild(host);

        service.addConnector(connector);
        service.setContainer(engine);

        //将server请求加到tomcat中
        tomcat.addServlet(contextPath,"dispatcher",new DispatcherServlet());
        //将请求路径做映射
        context.addServletMappingDecoded("/*","dispatcher");

        //=================================
        //以下启动tomcat

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
