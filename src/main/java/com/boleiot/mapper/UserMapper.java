package com.boleiot.mapper;

import com.boleiot.model.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    User getUserByName(@Param("username") String username);

    void correlationRoles(Integer userId, Integer... roleIds);

    void uncorrelationRoles(Integer userId, Integer... roleIds);

    Set<String> findRoles(@Param("username") String username);

    Set<String> findPermissions(@Param("username") String username);
}