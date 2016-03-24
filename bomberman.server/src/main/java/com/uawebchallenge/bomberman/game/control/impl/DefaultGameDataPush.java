package com.uawebchallenge.bomberman.game.control.impl;

import com.uawebchallenge.bomberman.game.control.GameDataPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultGameDataPush implements GameDataPush{

    private final static Logger logger = LoggerFactory.getLogger(DefaultGameDataPush.class);

    @Override
    public void push(String gameId, String data) {
        logger.info("Pushing data for gameId=" + gameId);
        logger.info(data);
        logger.info("--------------------------------------");
    }
}
