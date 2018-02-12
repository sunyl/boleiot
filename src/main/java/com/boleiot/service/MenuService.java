package com.boleiot.service;

import com.boleiot.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenuList(String role);

}
