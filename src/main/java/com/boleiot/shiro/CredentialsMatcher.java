package com.boleiot.shiro;

import com.boleiot.service.RedisService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

public class CredentialsMatcher extends HashedCredentialsMatcher {

    private static final Logger log = LoggerFactory.getLogger(CredentialsMatcher.class);

    private static String TAG = "retryCount_";

    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        String password = token.getCredentials().toString();
        String password2 = info.getCredentials().toString();
        log.info("--->doCredentialsMatch password = " + password + " password2 = " + password2);
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
            redisService.set(TAG + username, new AtomicInteger(0));
        }
        return match;
    }

}
