package com.youyuan.protocol.http;

import com.youyuan.framework.Invocation;
import com.youyuan.provider.LocalMapRegist;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangyu
 * @version 1.0
 * @description 处理server请求的类
 * @date 2019/7/11 18:22
 */
public class HttpServerHandler {

    /**
     * 处理server请求方法
     * @param req
     * @param resp
     * @return
     */
    public String handle(HttpServletRequest req, HttpServletResponse resp){
        try {
            //创建输入流
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);

            //读取客户端传入的参数
            Invocation invocation = (Invocation) objectInputStream.readObject();

            //获取接口名
            String interfaceName=invocation.getInterfaceName();
            //获取本地注册的实现类
            Class invokClass= LocalMapRegist.getClass(interfaceName);
            //构建执行的方法
            Method method=invokClass.getMethod(invocation.getMethodName(),invocation.getParamType());

            //通过反射执行方法
            String result = (String) method.invoke(invokClass.newInstance(), invocation.getParams());
            //结果写回输出流
            IOUtils.write(result,resp.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
