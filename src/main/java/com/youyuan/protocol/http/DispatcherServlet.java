package com.youyuan.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟servlet接收请求
 * @date 2019/7/11 18:19
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * 重写父类的service方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new HttpServerHandler().handle(req,resp);
    }
}
