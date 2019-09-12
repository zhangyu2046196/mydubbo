package com.youyuan.protocol.http;

import com.youyuan.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhangyu
 * @version 1.0
 * @description 消费者调用提供者的客户端工具类
 * @date 2019/7/11 19:26
 */
public class HttpClient {

    /**
     * post请求
     * @param hostName 服务地址
     * @param port 端口
     * @param invocation 参数类
     * @return 返回请求响应报文
     */
    public static String post(String hostName, Integer port, Invocation invocation){
        URL url=null;
        try {
            //创建url
            url=new URL("http",hostName,port,"/");
            //建立连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //创建输出流
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);

            //将客户端参数写入输出流
            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();

            objectOutputStream.close();
            outputStream.close();

            //接收服务提供者响应报文
            InputStream inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
