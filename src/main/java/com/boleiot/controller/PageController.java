package com.boleiot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/add_terminal")
    public String addTerminal() {
        return "fragments/add_terminal";
    }

    @RequestMapping("/home")
    public String home() {
        return "fragments/home";
    }

    @RequestMapping("/terminal_list")
    public String terminalList() {
        return "fragments/terminal_list";
    }

    @RequestMapping("/terminal_chat")
    public String terminalChat() {
        return "fragments/terminal_chat";
    }
}
