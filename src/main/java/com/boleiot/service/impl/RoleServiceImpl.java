package com.boleiot.service.impl;

import com.boleiot.mapper.RoleMapper;
import com.boleiot.model.user.Role;
import com.boleiot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    public Role createRole(Role role) {
        return roleMapper.createRole(role);
    }

    public void deleteRole(Integer roleId) {
        roleMapper.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Integer roleId, Integer... permissionIds) {
        roleMapper.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Integer roleId, Integer... permissionIds) {
        roleMapper.uncorrelationPermissions(roleId, permissionIds);
    }

}
