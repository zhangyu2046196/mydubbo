package com.youyuan.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟服务提供者本地注册
 * @date 2019/7/11 19:18
 */
public class LocalMapRegist {

    //key:接口名 value:实现类
    private static Map<String, Class> REGISTE=new HashMap<String, Class>();

    /**
     * 本地注册存储
     * @param interfaceName 接口名
     * @param T 实现类
     */
    public static void regist(String interfaceName,Class T){
        REGISTE.put(interfaceName,T);
    }

    /**
     * 根据接口名查询实现类
     * @param interfaceName 接口名
     * @return 返回实现类
     */
    public static Class getClass(String interfaceName){
        return REGISTE.get(interfaceName);
    }

}
