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
}
