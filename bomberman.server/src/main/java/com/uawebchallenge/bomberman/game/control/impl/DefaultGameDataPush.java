package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameDataPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefaultGameDataPush implements GameDataPush{

    private final static Logger logger = LoggerFactory.getLogger(DefaultGameDataPush.class);

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
