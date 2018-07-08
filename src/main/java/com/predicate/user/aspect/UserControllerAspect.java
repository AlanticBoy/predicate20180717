package com.predicate.user.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  11:27 2018/5/21
 * @ModefiedBy:
 */
@Aspect
public class UserControllerAspect {

    private String startTimeMillis; // 开始时间
    private String endTimeMillis; // 结束时间

    @Before("execution(* com.predicate.user.controller..*.*(..))")
    public void deBeforeIntoMethod() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        startTimeMillis = format.format(date);
        //  startTimeMillis = String.valueOf(System.currentTimeMillis()); // 记录方法开始执行的时间
        System.out.println(" 进入方法前的执行时间 " + startTimeMillis);

    }

    @Around("execution(* com.predicate.user.controller..*.*(..))")
    public void doAroundMethod(ProceedingJoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String requestedUri = request.getRequestURI();

        try {
            Object result = pjp.proceed();// result的值就是被拦截方法的返回值
            System.out.println(" 被拦截方法的返回值  " + result);
            System.out.println("  当前用户访问的路径是 :  " + requestedUri);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @After("execution(* com.predicate.user.controller..*.*(..))")
    public void doAfterOutMethod() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        endTimeMillis = format.format(date);
        System.out.println(" 离开方法的时间  " + endTimeMillis);
    }


}
