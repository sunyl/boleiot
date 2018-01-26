package com.boleiot.controller;

import com.boleiot.service.DeviceService;
import com.boleiot.utils.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
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

    @RequestMapping(value = "/terminal_chat", method = RequestMethod.GET)
    public String terminalChat(@RequestParam("no") String no, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("address") String address, Model model) {
        model.addAttribute("no", no);
        model.addAttribute("name", name);
        model.addAttribute("register", "no=" + no + "&pw=" + password);
        model.addAttribute("address", address);
        return "fragments/terminal_chat";
    }
}
