package com.boleiot.shiro;

import com.boleiot.model.user.User;
import com.boleiot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 验证用户身份
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) authcToken;
        String username = utoken.getUsername();
        User user = userService.getUserByName(username);
        System.out.println("doGetAuthenticationInfo 验证用户身份 username = " + username);
        if (null == user) {
            throw new UnknownAccountException();
        } else if (user.getStatus() == 0) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(),
                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        System.out.println("doGetAuthorizationInfo 授权 username = " + username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }
}
