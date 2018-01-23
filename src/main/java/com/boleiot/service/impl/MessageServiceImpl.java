package com.boleiot.service.impl;

import com.boleiot.model.SocketMessage;
import com.boleiot.service.MessageService;
import com.boleiot.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendMessage(String name, String no, String content) {
        SocketMessage chatMessage = createMessage(name, no, content);
        template.convertAndSendToUser("admin", "/topic/response", JsonUtil.toJson(chatMessage));
    }

    @Override
    public void sendGroupMessage(String groupName, int groupId, String content) {
        SocketMessage message = createMessage(groupName, String.valueOf(groupId), content);
        template.convertAndSend("/topic/response", JsonUtil.toJson(message));
    }


    private SocketMessage createMessage(String name, String no, String content) {
        SocketMessage message = new SocketMessage();
        message.setName(name);
        message.setType("text");
        message.setNo(no);
        message.setContent(content);
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }
}
