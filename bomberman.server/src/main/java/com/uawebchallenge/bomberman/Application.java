package com.uawebchallenge.bomberman;

import com.uawebchallenge.bomberman.config.ApplicationConfig;
import com.uawebchallenge.bomberman.game.control.GameManager;
import com.uawebchallenge.bomberman.game.control.impl.DefaultGameManager;
import com.uawebchallenge.bomberman.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class Application {

    @Autowired
    private GameManager gameManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
