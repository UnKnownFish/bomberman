package com.uawebchallenge.bomberman.game.utils;

import com.uawebchallenge.bomberman.game.model.GameField;
import com.uawebchallenge.bomberman.game.model.GameFieldItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GameFieldGeneratorTest {

    public static final int CLASSIC_WIDTH = 13;
    public static final int CLASSIC_HEIGHT = 11;

    private GameField defaultGameField;

    @Before
    public void setUp() {
        defaultGameField = GameFieldGenerator.generateGameField(CLASSIC_WIDTH, CLASSIC_HEIGHT);
    }

    @Test
    public void generatorShouldCreateField() {
        assertThat(defaultGameField, is(notNullValue()));
    }

    @Test
    public void fieldShouldHaveSpecifiedSize() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        assertThat(fieldItems, allOf(is(notNullValue()), arrayWithSize(CLASSIC_HEIGHT)));
        Arrays.stream(fieldItems).forEach(row -> assertThat(row, allOf(is(notNullValue()), arrayWithSize(CLASSIC_WIDTH))));
    }

    @Test
    public void everyCellShouldContainElement() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        for (int row = 0; row < CLASSIC_HEIGHT; row++) {
            for (int col = 0; col < CLASSIC_WIDTH; col++) {
                String msg = String.format("Field at row=%d column=%d is null", row, col);
                assertNotNull(msg, fieldItems[row][col]);
            }
        }
    }

    @Test
    public void fieldShouldHaveGridOfStones() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        for (int row = 0; row < CLASSIC_HEIGHT; row++) {
            for (int col = 0; col < CLASSIC_WIDTH; col++) {
                if ((row + 1) % 2 + (col + 1) % 2 == 0) {
                    assertThat(fieldItems[row][col], allOf(is(notNullValue()), equalTo(GameFieldItem.STONE)));
                }
            }
        }
    }

    @Test
    public void topLeftCornersShouldBeEmpty() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        assertThat(fieldItems[0][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[1][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[0][1], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void topRightCornersShouldBeEmpty() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        assertThat(fieldItems[0][11], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[0][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[1][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void bottomLeftCornersShouldBeEmpty() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        assertThat(fieldItems[10][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[10][1], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[9][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void bottomRightCornersShouldBeEmpty() {
        final GameFieldItem[][] fieldItems = defaultGameField.getFieldItems();
        assertThat(fieldItems[0][11], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[10][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[9][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }
}