package com.mygdx.game.gamestate;

import com.mygdx.game.tetromino.Tetromino;

public class CurrentPiece {
    private Tetromino tetromino;
    private int x;
    private int y;
    private int rotation;

    public CurrentPiece(Tetromino tetromino, int x, int y) {
        this.tetromino = tetromino;
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }

    public CurrentPiece(Tetromino tetromino, int x, int y, int rotation) {
        this.tetromino = tetromino;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public void changeBy(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
