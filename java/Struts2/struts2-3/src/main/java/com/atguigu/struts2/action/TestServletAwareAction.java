package com.atguigu.struts2.action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过实现 ServletXxxAware 接口的方式可以由 Struts2 注入所需的 Servlet 相关的对象
 *
 * ServletContextAware：注入 ServletContext 对象（常用）
 * ServletRequestAware：注入 HttpServletRequest 对象（常用）
 * ServletResponseAware：HttpServletResponse
 *
 * 其他对象则可以间接获取，如 HttpSession 等，可以通过 HttpServletRequest 对象来获取
 *
 * @author: yepz
 * @date: 2020/3/29
 */
public class TestServletAwareAction implements ServletContextAware, ServletRequestAware, ServletResponseAware {

    private ServletContext context;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        System.out.println(request);
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        System.out.println(response);
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.context = context;
        System.out.println(context);
    }

    public String execute() {
        System.out.println("ServletContext: " + context);
        return "success";
    }
}
