package com.boleiot.shiro;

import com.boleiot.service.RedisService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class CredentialsMatcher extends HashedCredentialsMatcher {
    private static String TAG = "retryCount_";

    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = (AtomicInteger) redisService.get(TAG + username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            redisService.set(TAG + username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            redisService.set(TAG + username, 0);
        }
        return match;
    }

}
