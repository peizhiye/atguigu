package com.atguigu.struts2.action;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: yepz
 * @date: 2020/3/29
 */
public class TestServletActionContextAction {

    public String execute() {

        /*
         * ServletActionContext：可以从中获取到当前 Action 对象需要的一切 Servlet API 相关的对象。
         * 常用的方法：
         * 1、获取 ServletContext：ServletActionContext.getServletContext()
         * 2、获取 HttpServletRequest：ServletActionContext.getRequest()
         * 3、获取 HttpSession：ServletActionContext.getRequest().getSession()
         */
        ServletContext servletContext = ServletActionContext.getServletContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();

        System.out.println("execute...");

        return "success";
    }
}
