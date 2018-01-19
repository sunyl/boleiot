package com.boleiot.service.impl;

import com.boleiot.model.User;
import com.boleiot.sercurity.UserPrincipal;
import com.boleiot.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String ROLE_TAG = "USER";
    private final static String USER_NAME = "admin";
    private final static String USER_PWD = "123456";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setName(USER_NAME);
        user.setPassword(USER_PWD);
        user.setRole(ROLE_TAG);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
        UserPrincipal userPrincipal = new UserPrincipal(username, user.getPassword(), authorities);
        return userPrincipal;
    }
}
