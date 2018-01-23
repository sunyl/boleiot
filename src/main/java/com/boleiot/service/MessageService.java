package com.boleiot.service;

public interface MessageService {

    void sendMessage(String name, String no, String content);

    void sendGroupMessage(String groupName, int groupId, String content);
}
