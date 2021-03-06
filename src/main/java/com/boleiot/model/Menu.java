package com.boleiot.model;

import java.util.*;

public class Menu extends BaseModel {

    private int id;
    private String title;
    private String url;
    private String icon;
    private int parentId;
    private int sort;
    private int state;
    private int role;

    private List<Menu> children = new ArrayList<>();

    public void sortChildren() {
        Collections.sort(children, (Menu menu1, Menu menu2) -> {
            int result = 0;
            int ordby1 = menu1.getSort();
            int ordby2 = menu2.getSort();
            int id1 = menu1.getId();
            int id2 = menu2.getId();
            if (0 != ordby1 && 0 != ordby2) {
                result = (ordby1 < ordby2 ? -1 : (ordby1 == ordby2 ? 0 : 1));
            } else {
                result = (id1 < id2 ? -1 : (id1 == id2 ? 0 : 1));
            }
            return result;
        });
        for (Iterator<Menu> it = children.iterator(); it.hasNext(); ) {
            it.next().sortChildren();
        }
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public static List<Menu> createTreeMenus(List<Menu> menus) {
        List<Menu> treeMenus = null;
        if (null != menus && !menus.isEmpty()) {
            Menu root = new Menu();
            root.setTitle("菜单根目录");
            Map<Integer, Menu> dataMap = new HashMap<>();
            for (Menu menu : menus) {
                dataMap.put(menu.getId(), menu);
            }
            // 树形结构
            Set<Map.Entry<Integer, Menu>> entrySet = dataMap.entrySet();
            for (Map.Entry<Integer, Menu> entry : entrySet) {
                Menu menu = entry.getValue();
                if (0 == menu.getParentId()) {
                    root.getChildren().add(menu);
                } else {
                    dataMap.get(menu.getParentId()).getChildren().add(menu);
                }
            }
            root.sortChildren();
            treeMenus = root.getChildren();
        }
        return treeMenus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
