package com.uawebchallenge.bomberman.game.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.GameFieldItem;
import com.uawebchallenge.bomberman.game.model.Player;
import lombok.Value;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsonBuilder {

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(GameFieldItem.class, new GameFieldItemSerializer());
        mapper.registerModule(module);
    }

    public static String toJson(Game game) throws JsonProcessingException {
        GameJsonView gameJsonView = new GameJsonView(game);
        return mapper.writeValueAsString(gameJsonView);
    }


    private static class GameFieldItemSerializer extends JsonSerializer<GameFieldItem> {

        public void serialize(GameFieldItem value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeNumber(value.getItemId());
        }
    }

    @Value
    private static class PlayerJsonView {
        private String id;
        private double x;
        private double y;
    }

    @Value
    private static class GameJsonView {
        @JsonProperty("players")
        List<PlayerJsonView> playerJsonViews;

        @JsonProperty("field")
        GameFieldItem[][] fieldItems;

        public GameJsonView(Game game) {
            this.fieldItems = game.getGameField().getFieldItems();
            this.playerJsonViews = new LinkedList<>();

            for (Player player : game.getPlayerList()) {
                PlayerJsonView playerJsonView = new PlayerJsonView(player.getPlayerId(), player.getPositionX(), player.getPositionY());
                this.playerJsonViews.add(playerJsonView);
            }
        }
    }
}
