package com.predicate.user.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  20:51 2018/5/12
 * @ModefiedBy:
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        System.out.println(" 进入到springmvc的拦截器  get  URI  " + uri);

        if (uri.equals("/user/login.action")) {
            return true;
        }
        /*原来在我们要转发的地址前面加上“/”与不加斜杠是有区别的
        * 区别在于：加上斜杠表示绝对路径。不加斜杠表示相对路径
        * */
        httpServletRequest.getRequestDispatcher("/jsps/addUser.jsp").forward(httpServletRequest,httpServletResponse);

        /*
        * return true表示如果满足条件会进入我们请求的action
        * return false表示不满足条件不能进入请求路径
        * */
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
