package com.youyuan.framework;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟放到注册中心的服务地址端口信息
 * @date 2019/7/11 18:38
 */
public class URL implements Serializable {
    /**
     * 服务地址
     */
    private String hostName;

    /**
     * 端口
     */
    private Integer port;

    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "URL{" +
                "hostName='" + hostName + '\'' +
                ", port=" + port +
                '}';
    }
}
