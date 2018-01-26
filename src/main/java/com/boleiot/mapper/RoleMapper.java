package com.boleiot.mapper;

import com.boleiot.model.user.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {

    Role createRole(Role role);

    void deleteRole(Integer roleId);

    Role selectByPrimaryKey(@Param("id") Integer id);

    void correlationPermissions(Integer roleId, Integer... permissionIds);

    void uncorrelationPermissions(Integer roleId, Integer... permissionIds);

}
