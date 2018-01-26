package com.boleiot.service.impl;


import com.boleiot.mapper.PermissionMapper;
import com.boleiot.model.user.Permission;
import com.boleiot.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Permission createPermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    public void deletePermission(Integer permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }
}
