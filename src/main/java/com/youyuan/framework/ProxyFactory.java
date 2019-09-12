package com.youyuan.framework;


import com.youyuan.protocol.http.HttpClient;
import com.youyuan.register.RemoteMapRegist;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangyu
 * @version 1.0
 * @description 动态代理工厂
 * @date 2019/7/11 20:33
 */
public class ProxyFactory {

    /**
     * 创建动态代理，接口调用动态代理方法会进入这个方法
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation=new Invocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);
                URL url= RemoteMapRegist.getUrl(interfaceClass.getName());
                return HttpClient.post(url.getHostName(),url.getPort(),invocation);
            }
        });
    }

}
