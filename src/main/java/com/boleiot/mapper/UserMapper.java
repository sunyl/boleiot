package com.boleiot.mapper;

import com.boleiot.model.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}