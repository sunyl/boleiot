package com.boleiot.service;

import com.boleiot.model.user.Role;

public interface RoleService {


    Role createRole(Role role);

    void deleteRole(Integer roleId);

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Integer roleId, Integer... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Integer roleId, Integer... permissionIds);

}
