package com.predicate.shiro.shiroFilter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:17 2018/5/19
 * @ModefiedBy:
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

    private String kickUrl;//踢出后的地址
    private boolean kickoutAfter = false;//默认踢出之前登陆的用户
    private int maxSession = 1;//同一账号最大的登录数
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickUrl(String kickUrl) {
        this.kickUrl = kickUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager){
        this.cache=cacheManager.getCache("kickout_session");
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println(" 进入单点登录的onAccessdenied ");
        /*得到当前用户*/
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            System.out.println(" 该用户暂时还没登陆！ ");
            //如果没有登录直接进行登陆流程
            return true;
        }
        Session session = subject.getSession();
        String phone = String.valueOf(subject.getPrincipal());
        Serializable sessionId = session.getId();

        //同步控制
        //Deque其实是栈，先进后出
        Deque<Serializable> deque=cache.get(phone);
        if (deque==null){
            System.out.println(" Deque里还没有该用户的信息，新建Deque并存放到缓存里 ");
            deque=new LinkedList<Serializable>();
            cache.put(phone,deque);
        }
        //如果队列里没有次sessionId，并且用户没有被踢出，放入队列
        if(!deque.contains(sessionId)&&session.getAttribute("kickout")==null){
            System.out.println(" 队列没有sessionId    ");
            deque.push(sessionId);
        }
        while (deque.size()>maxSession){
            System.out.println(" 当前用户帐号的登录数目 "+deque.size());
            Serializable kickoutSessionId=null;
            /*选择踢出前者或者后者*/
            if(kickoutAfter){
                kickoutSessionId=deque.removeFirst();
            }else {
                System.out.println(" 踢出第一个用户。。 ");
                kickoutSessionId=deque.removeLast();
            }

            try {
                Session kickoutSession=sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession!=null){
                    System.out.println("  得到了用户之前的session ");
                    /*设置kickoutSession属性表示被踢出*/
                    kickoutSession.setAttribute("kickout",true);
                }
            } catch (SessionException e) {
                e.printStackTrace();
            }

        }
        /*如果被踢出了，重定向到提出后的地址*/
        if(session.getAttribute("kickout")!=null){
            /*结束会话*/
            try {
                System.out.println(" 结束回哈会话 ");
                subject.logout();
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveRequest(servletRequest);
            WebUtils.issueRedirect(servletRequest,servletResponse,kickUrl);
            return false;
        }
        return true;
    }
}
