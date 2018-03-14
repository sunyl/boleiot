package com.boleiot.shiro;

import com.boleiot.service.RedisService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialsMatcher extends HashedCredentialsMatcher {

    private static final Logger log = LoggerFactory.getLogger(CredentialsMatcher.class);

    private static String TAG = "retryCount_";
    private static int timeout = 300;
    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        String password = token.getCredentials().toString();
        String password2 = info.getCredentials().toString();
        log.info("--->doCredentialsMatch password = " + password + " password2 = " + password2);
        Object cacheCount = redisService.get(TAG + username);
        int retryCount = 0;
        if (cacheCount != null) {
            retryCount = (int) cacheCount;
        }
        if (retryCount >= 5) {
            throw new ExcessiveAttemptsException();
        }
        retryCount = retryCount + 1;
        redisService.set(TAG + username, retryCount, timeout);
        boolean match = super.doCredentialsMatch(token, info);
        if (match) {
            redisService.set(TAG + username, 0, timeout);
        }
        return match;
    }

}
