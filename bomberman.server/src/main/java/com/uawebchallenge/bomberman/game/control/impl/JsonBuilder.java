package com.uawebchallenge.bomberman.game.control.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.uawebchallenge.bomberman.game.model.Game;
import com.uawebchallenge.bomberman.game.model.field.GameFieldItem;
import com.uawebchallenge.bomberman.game.model.player.Player;
import lombok.Value;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class JsonBuilder {

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
        private final String id;
        private final double x;
        private final double y;
    }

    @Value
    private static class GameJsonView {
        @JsonProperty("players")
        private final List<PlayerJsonView> playerJsonViews;

        @JsonProperty("field")
        private final GameFieldItem[][] fieldItems;

        public GameJsonView(Game game) {
            this.fieldItems = game.getGameField().getFieldItems();

            List<Player> playerList = game.getPlayerList();
            this.playerJsonViews = playerList.stream()
                    .map(p -> new PlayerJsonView(p.getPlayerId(), p.getPositionX(), p.getPositionY()))
                    .collect(Collectors.toList());
        }
    }
}
