package com.xuenan.lab.filter;

import com.xuenan.lab.entity.LoginSession;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.user_management.dao.UserDao;
import com.xuenan.lab.user_management.service.LoginSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ManagerFilter implements Filter {


    @Autowired
    private LoginSessionService loginSessionService  ;

    @Autowired
    private UserDao userDao ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ac = WebApplicationContextUtils .getWebApplicationContext(context);
        loginSessionService = ac.getBean(LoginSessionService.class);
        userDao = ac.getBean(UserDao.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        HttpServletResponse response = (HttpServletResponse) servletResponse ;

        String token = request.getHeader("token") ;
        if( token == null ){
            request.getRequestDispatcher("/error/token/null").forward(request,response);
        } //如果在请求的Header中没有“token”字段，则返回错误代码
        else{
            LoginSession session = loginSessionService.queryValidLoginSessionByToken(token);
            if( session  == null ){
                request.getRequestDispatcher("/error/token/invalid").forward(request,response);
            } else{
                User user = userDao.queryUserById(session.getUser().getId());
                if( user == null || user.getValid() == 0 ){
                    request.getRequestDispatcher("/error/token/invalid").forward(request,response);
                }
                else if( user.getType()<3){
                    request.getRequestDispatcher("/error/token/not_manager").forward(request,response);
                }else {
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
