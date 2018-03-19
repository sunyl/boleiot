package com.boleiot.controller;

import com.boleiot.model.Menu;
import com.boleiot.model.user.Role;
import com.boleiot.service.DeviceService;
import com.boleiot.service.MenuService;
import com.boleiot.service.RoleService;
import com.boleiot.service.UserService;
import com.boleiot.utils.UidUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class PageController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        Set<String> roles = userService.findRoles(SecurityUtils.getSubject().getPrincipal().toString());
        String roleType = "normal";
        Iterator<String> iterator = roles.iterator();
        if (iterator.hasNext()) {
            roleType = iterator.next();
        }
        Role role = roleService.getRoleByType(roleType);
        List<Menu> menus = menuService.getMenuList(role.getId());
        List<Menu> treeMenu = Menu.createTreeMenus(menus);
        model.addAttribute("menu", treeMenu);
        return "index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

    @RequestMapping("/add_terminal")
    public String addTerminal(Model model) {
        model.addAttribute("no", UidUtil.getUUID_8());
        return "fragments/add_terminal";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        int deviceCount = deviceService.getCount(null);
        model.addAttribute("deviceCount", deviceCount);
        return "fragments/home";
    }

    @RequestMapping("/terminal_list")
    public String terminalList() {
        return "fragments/terminal_list";
    }

    @RequestMapping("/modif_password")
    public String modifPassword() {
        return "fragments/modif_password";
    }

    @RequestMapping(value = "/terminal_chat", method = RequestMethod.GET)
    public String terminalChat(@RequestParam("no") String no, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("address") String address, Model model) {
        model.addAttribute("no", no);
        model.addAttribute("name", name);
        model.addAttribute("register", "no=" + no + "&pw=" + password);
        model.addAttribute("address", address);
        return "fragments/terminal_chat";
    }
}
