package com.boleiot.service.impl;

import com.boleiot.mapper.UserMapper;
import com.boleiot.model.user.User;
import com.boleiot.service.UserService;
import com.boleiot.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private PasswordHelper passwordHelper = new PasswordHelper();

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int createUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int changePassword(String username, String newPassword) {
        User user = userMapper.getUserByName(username);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void correlationRoles(Integer userId, Integer... roleIds) {
        userMapper.correlationRoles(userId, roleIds);
    }

    @Override
    public void uncorrelationRoles(Integer userId, Integer... roleIds) {
        userMapper.uncorrelationRoles(userId, roleIds);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userMapper.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userMapper.findPermissions(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
