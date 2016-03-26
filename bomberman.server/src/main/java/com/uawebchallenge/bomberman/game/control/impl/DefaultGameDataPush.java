package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameDataPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefaultGameDataPush implements GameDataPush {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public DefaultGameDataPush(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void push(String gameId, String data) {
        String topic = "/topic/" + gameId;
        messagingTemplate.convertAndSend(topic, data);
    }
}
