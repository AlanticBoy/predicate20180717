package com.predicate.shiro.credial;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  12:32 2018/5/18
 * @ModefiedBy:
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String,AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager){
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println("  进入到  RetryLimitCredentialsMatcher 方法  ");
        String userName = token.getPrincipal().toString();
        AtomicInteger retryCount = passwordRetryCache.get(userName);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userName,retryCount);
        }
        if (retryCount.incrementAndGet() > 3) {
            System.out.println(" 超过3次登录    ");
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches){
            passwordRetryCache.remove(userName);
        }
        System.out.println(" matches 方法执行完毕  ");
        return matches;
    }
}
