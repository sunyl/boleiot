package com.boleiot.controller;


import com.boleiot.handle.MsgHandlerManager;
import com.boleiot.model.Device;
import com.boleiot.model.SocketMessage;
import com.boleiot.service.DeviceService;
import com.boleiot.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private DeviceService deviceService;

    @MessageMapping("/chat")
    public void chat(Principal principal, SocketMessage message) {
        System.out.println("WebSocket chat --->name = " + principal.getName() + " message = " + JsonUtil.toJson(message));
        Device device = deviceService.selectByNo("aabfaeaf5b3743d89f8468e1fa4963bd");
        String id = device.getHostname() + ":" + device.getPort();
        MsgHandlerManager.get().sendMessage(id, message.getContent());
    }
}
