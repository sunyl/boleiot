package com.boleiot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = {"/login", "/"})
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/add_terminal")
    public String addTerminal() {
        System.out.println("--->page add_terminal");
        return "fragments/add_terminal";
    }

    @RequestMapping("/home")
    public String home() {
        System.out.println("--->page home");
        return "fragments/home";
    }
}
