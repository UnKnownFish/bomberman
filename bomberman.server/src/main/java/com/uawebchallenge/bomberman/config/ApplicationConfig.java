package com.uawebchallenge.bomberman.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.uawebchallenge.bomberman.game.control",
        "com.uawebchallenge.bomberman.api"
})
public class ApplicationConfig {
}
