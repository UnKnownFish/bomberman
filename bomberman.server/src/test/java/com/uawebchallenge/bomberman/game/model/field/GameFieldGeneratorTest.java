package com.uawebchallenge.bomberman.game.model.field;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GameFieldGeneratorTest {

    public static final int CLASSIC_WIDTH = 13;
    public static final int CLASSIC_HEIGHT = 11;

    private GameFieldItem[][] fieldItems;

    @Before
    public void setUp() {
        fieldItems = GameFieldGenerator.generateGameField(CLASSIC_WIDTH, CLASSIC_HEIGHT);
    }

    @Test
    public void generatorShouldCreateField() {
        assertThat(fieldItems, is(notNullValue()));
    }

    @Test
    public void fieldShouldHaveSpecifiedSize() {
        assertThat(fieldItems, allOf(is(notNullValue()), arrayWithSize(CLASSIC_HEIGHT)));
        Arrays.stream(fieldItems).forEach(row -> assertThat(row, allOf(is(notNullValue()), arrayWithSize(CLASSIC_WIDTH))));
    }

    @Test
    public void everyCellShouldContainElement() {
        for (int row = 0; row < CLASSIC_HEIGHT; row++) {
            for (int col = 0; col < CLASSIC_WIDTH; col++) {
                String msg = String.format("Field at row=%d column=%d is null", row, col);
                assertNotNull(msg, fieldItems[row][col]);
            }
        }
    }

    @Test
    public void fieldShouldHaveGridOfStones() {
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
        assertThat(fieldItems[0][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[1][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[0][1], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void topRightCornersShouldBeEmpty() {
        assertThat(fieldItems[0][11], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[0][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[1][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void bottomLeftCornersShouldBeEmpty() {
        assertThat(fieldItems[10][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[10][1], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[9][0], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }

    @Test
    public void bottomRightCornersShouldBeEmpty() {
        assertThat(fieldItems[0][11], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[10][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
        assertThat(fieldItems[9][12], allOf(is(notNullValue()), is(equalTo(GameFieldItem.EMPTY))));
    }
}