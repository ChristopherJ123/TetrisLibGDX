package com.mygdx.game.controls;

import com.mygdx.game.gamestate.Board;

public interface Moveable {
    boolean moveLeft();
    boolean moveRight();
    boolean moveDown();
    boolean rotateLeft();
    boolean rotateRight();
    boolean rotate180();
}
