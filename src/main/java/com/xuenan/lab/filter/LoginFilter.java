package com.xuenan.lab.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此过滤器过滤所有未登录用户对某些接口的请求到/error路径下
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        HttpServletResponse response = (HttpServletResponse) servletResponse ;
        String token = request.getHeader("token") ;
        if( token == null ){
            request.getRequestDispatcher("/error/session/invalid").forward(request,response);
        } //如果在请求的Header中没有“token”字段，则返回错误代码
        else {
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
