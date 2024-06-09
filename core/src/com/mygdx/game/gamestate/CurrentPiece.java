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

    public boolean changeBy(int x, int y, Board board) {
        for (int i = 0; i < tetromino.getShapeBoolean().length; i++) {
            for (int j = 0; j < tetromino.getShapeBoolean()[i].length; j++) {
                if (tetromino.getShapeBoolean()[i][j]) {
                    // Return false if failed to move because hit an obstacle
                    try {
                        if (board.playfield[y+i+this.y][x+j+this.x].isPlaced()) return false;
                    } catch (ArrayIndexOutOfBoundsException e) {return false;}
                }
            }
        }
        this.x += x;
        this.y += y;
        return true; // Return true if successfully moved
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
