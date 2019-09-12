package com.youyuan.register;

import com.youyuan.framework.URL;

import java.io.*;
import java.util.*;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟远程注册中心
 * @date 2019/7/11 18:35
 */
public class RemoteMapRegist {

    //用来存储服务提供者地址端口，模拟zookeeper
    private static Map<String,List<URL>> REGISTER=new HashMap<String,List<URL>>();


    /**
     * 服务提供者注册服务到注册中心
     * @param interfaceName 接口名
     * @param url 服务地址端口
     */
    public static void regist(String interfaceName,URL url){
        List<URL> list=Collections.singletonList(url);
        REGISTER.put(interfaceName,list);

        saveFile();//模拟存储redis zookeeper

    }

    /**
     * 模拟随机负载均衡算法获取请求地址
     * @param interfaceName
     * @return
     */
    public static URL getUrl(String interfaceName){

        REGISTER=getFile(); //模拟从注册中心读取信息

        List<URL> urlList = REGISTER.get(interfaceName);
        Random random=new Random();
        return urlList.get(random.nextInt(urlList.size()));
    }

    /**
     * 因为服务提供者消费者是跨进程访问，为了模拟保存在redis或zookeeper，索引保存在本地文件
     */
    private static void saveFile(){
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream("/tmp.txt");
            objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从本地文件获取注册中心内容信息
     * @return
     */
    private static Map<String,List<URL>> getFile(){
        FileInputStream fInputStream=null;
        ObjectInputStream oInputStream=null;
        try {
            fInputStream=new FileInputStream("/tmp.txt");
            oInputStream=new ObjectInputStream(fInputStream);
            return (Map<String, List<URL>>) oInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<URL> list= Collections.singletonList(new URL("localhost",8087));
        System.out.println(list);
    }

}
