package com.mygdx.game.controls;

import com.mygdx.game.gamestate.Board;

public interface Moveable {
    boolean moveLeft(Board board);
    boolean moveRight(Board board);
    boolean moveDown(Board board);
    boolean rotateLeft(Board board);
    boolean rotateRight(Board board);
    boolean rotate180(Board board);
}
