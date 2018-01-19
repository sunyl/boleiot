package com.boleiot.service;

public interface MessageService {

    void sendMessage(String no, String name, String content);

    void sendGroupMessage(String group, String content);
}
