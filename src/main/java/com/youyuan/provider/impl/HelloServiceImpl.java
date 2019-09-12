package com.youyuan.provider.impl;

import com.youyuan.provider.api.HelloService;

/**
 * @author zhangyu
 * @version 1.0
 * @description 定义接口的实现了  provider第二步
 * @date 2019/7/11 17:21
 */
public class HelloServiceImpl implements HelloService {
    /**
     * 接口实现类
     * @param userName 用户名
     * @return 返回逻辑处理后内容
     */
    public String sayHello(String userName) {
        return "自定义dubbo框架:"+userName;
    }
}
