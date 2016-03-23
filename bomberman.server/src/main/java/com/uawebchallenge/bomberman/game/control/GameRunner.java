package com.uawebchallenge.bomberman.game.control;

import com.uawebchallenge.bomberman.game.model.Game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameRunner {

    private final Game game;
    private ScheduledFuture<?> scheduledFuture;

    public GameRunner(Game game) {
        this.game = game;
    }

    public void run() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        int time = game.getConfig().getTime();
        scheduledFuture = scheduler.scheduleAtFixedRate(new GameRunnable(), 0, time, TimeUnit.MILLISECONDS);
    }

    private class GameRunnable implements Runnable {

        @Override
        public void run() {
            // TODO Implement GameLogic here
            System.out.println("Time:" + System.currentTimeMillis());

            if (game.isOver()) {
                scheduledFuture.cancel(true);
            }
        }
    }
}
