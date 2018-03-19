package com.boleiot.mapper;

import com.boleiot.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    List<Menu> getMenuList(@Param("role") Integer role);
}
