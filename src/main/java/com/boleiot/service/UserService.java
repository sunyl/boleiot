package com.boleiot.service;

import com.boleiot.model.user.User;

import java.util.Set;

public interface UserService {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 创建用户
     *
     * @param user
     */
    int createUser(User user);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Integer userId, String newPassword);

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Integer userId, Integer... roleIds);


    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Integer userId, Integer... roleIds);

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);
}
