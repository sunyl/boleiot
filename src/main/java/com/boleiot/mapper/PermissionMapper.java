package com.boleiot.mapper;


import com.boleiot.model.user.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {

    Permission insert(Permission permission);

    void deleteByPrimaryKey(@Param("id") Integer id);

    Permission selectByPrimaryKey(@Param("id") Integer id);
}
