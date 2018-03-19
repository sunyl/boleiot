package com.boleiot.service.impl;

import com.boleiot.mapper.MenuMapper;
import com.boleiot.model.Menu;
import com.boleiot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(Integer role) {
        return menuMapper.getMenuList(role);
    }
}
