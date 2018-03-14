package com.boleiot.controller;


import com.boleiot.handle.MsgHandlerManager;
import com.boleiot.handle.UdpInBoundHandler;
import com.boleiot.model.Device;
import com.boleiot.model.SocketMessage;
import com.boleiot.service.DeviceService;
import com.boleiot.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.security.Principal;

@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(UdpInBoundHandler.class);

    @Autowired
    private DeviceService deviceService;

    @MessageMapping("/chat")
    public void chat(Principal principal, SocketMessage message) {
        logger.info("WebSocket chat --->name = " + principal.getName() + " message = " + JsonUtil.toJson(message));
        Device device = deviceService.selectByNo(message.getNo());
        if (device != null && !StringUtils.isEmpty(device.getHostname()) && !StringUtils.isEmpty(device.getPort())) {
            String id = device.getHostname() + ":" + device.getPort();
            MsgHandlerManager.get().sendMessage(id, message.getContent());
        }
    }
}
