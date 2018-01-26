package com.boleiot.service;


import com.boleiot.model.user.Permission;

public interface PermissionService {
    
    Permission createPermission(Permission permission);

    void deletePermission(Integer permissionId);
}
