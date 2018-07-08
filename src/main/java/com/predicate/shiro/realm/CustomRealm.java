package com.predicate.shiro.realm;

import com.predicate.user.model.User;
import com.predicate.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:12 2018/5/17
 * @ModefiedBy:
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /*给用户授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println(" 进入到用户授权方法 ");
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
        // (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
        /*第一步，判断是否是非正常退出*/
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principal);
            SecurityUtils.getSubject().logout();
            return null;
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         /*第二步，取出用户名*/
        String phone = (String) principal.fromRealm(getName()).iterator().next();
        if (StringUtils.isNotBlank(phone)) {
            try {
                /*第三步，根据电话装载角色集合和权限集合*/
                Set<String> permissionstringSet = userService.selectPermissionsByPhone(phone);
                for (String permiss : permissionstringSet) {
                    info.addStringPermission(permiss);
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return info;
    }

    /*进行用户认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println(" 进入到用户认证方法 ");

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        /*第一步，获取用户名和密码这里的用户名说的是，可以作为登陆的帐号，如手机号，电子邮箱*/
        String phone = usernamePasswordToken.getUsername();
        String realm = getName();
        User user = userService.selectPasswordByPhone(phone);
        AuthenticationInfo info = null;
        if (user != null) {
            System.out.println(" password  " + user.getPassword());
               /*这里的phone和password是从数据库里得到的用户名和密码，
               realm是applicationContext_shiro.xml里的自定义realm
               我们在这里拿到SimpleAuthenticationInfo与/user/login.action里的UsernamepasswordToken进行比对。
               如果正确则矣，不正确抛异常
               * */
            info = new SimpleAuthenticationInfo(phone, user.getPassword(), realm);
        }
        System.out.println("  此时的AuthenticationInfo为   " + info);
        return info;
    }
}
