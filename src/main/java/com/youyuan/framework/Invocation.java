package com.youyuan.framework;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description  消费者调用服务提供传递的对象 需要序列化
 * @date 2019/7/11 19:23
 */
public class Invocation implements Serializable {
    private static final long serialVersionUID = -7923770063697086207L;

    /**
     * 接口名
     */
    private String interfaceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class[] paramType;
    /**
     * 参数
     */
    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class[] paramType, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramType = paramType;
        this.params = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramTyp=" + Arrays.toString(paramType) +
                ", param=" + Arrays.toString(params) +
                '}';
    }
}
