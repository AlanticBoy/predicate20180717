package com.predicate.shiro.shiroFilter;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  14:45 2018/5/19
 * @ModefiedBy:
 */
public class AuthorizationExpectionFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mapValue) throws Exception {
        System.out.println(" 进入authorizationExpectionFilter的isAccessAllowed方法 ");
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] perms = (String[]) mapValue;
        System.out.println(" 得到的所有权限  :");
        /*这边的得到的权限其实是从配置文件里获取到的*/
        for (String perm : perms) {
            System.out.print("  " + perm);
        }
        boolean isPermitted = true;
        if (perms.length > 0) {
            if (perms.length == 1) {
                if (!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else {
                if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
        }
        return isPermitted;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println(" 进入authorizationExpectionFilter的onAccessDenied方法 ");

        if (isAjax((HttpServletRequest) request)) {
            WebUtils.toHttp(response).sendError(404);
        } else {
            String unauthorizedUrl = getUnauthorizedUrl();
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }
        return super.onAccessDenied(request, response);
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        if (null != header && "XMLHttpRequest".endsWith(header)) {
            return true;
        }
        return false;
    }
}
