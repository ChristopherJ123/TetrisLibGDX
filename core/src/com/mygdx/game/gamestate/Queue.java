package com.mygdx.game.gamestate;

import com.mygdx.game.config.Config.Tetrominoes;
import com.mygdx.game.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Queue {
    private ArrayList<Tetrominoes> queue;

    public void generateQueueRandom7Bag() {
        List<Tetrominoes> list = Arrays.asList(Tetrominoes.values());
        if (queue.get(0) == null) {
            Collections.shuffle(list);
            for (int i = 0; i < 7; i++) {
                queue.set(i, list.get(i));
            }
        }
        if (queue.get(7) == null) {
            Collections.shuffle(list);
            for (int i = 7; i < 14; i++) {
                queue.set(i, list.get(i-7));
            }
        }
    }

    public Tetromino nextQueue() {
        generateQueueRandom7Bag();
        queue.add(null);
        return queue.remove(0).getType();
    }

    public Queue() {
        queue = new ArrayList<>();
        for (int i = 0; i < Tetrominoes.values().length * 2; i++) {
            queue.add(null);
        }
        generateQueueRandom7Bag();
    }

    public ArrayList<Tetrominoes> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<Tetrominoes> queue) {
        this.queue = queue;
    }

}
