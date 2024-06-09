package com.mygdx.game.gamestate;

import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.Tetromino;

import java.util.ArrayList;

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
        if (!hitObstacle(x, y, board)) {
            this.x += x;
            this.y += y;
            return true; // Return true if successfully moved
        }
        return false;
    }

    public boolean rotateBy(int r, Board board) {
        CurrentPiece currentPieceTemp = new CurrentPiece(tetromino, x, y, rotation);
        int width = tetromino.getShape().length;
        int height = tetromino.getShape()[0].length;
        Block[][] blocks = new Block[height][width];
        while (r < 0) r += 4;
        switch (r % 4) { // Rotate the shape
            case 1 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        blocks[height-1-j][i] = tetromino.getShape()[i][j];
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        blocks[height-1-j][width-1-i] = tetromino.getShape()[i][j];
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        blocks[j][width-1-i] = tetromino.getShape()[i][j];
                    }
                }
            }
            case 0 -> {
                return true;
            }
        }
        currentPieceTemp.tetromino.setShape(blocks);
        switch (rotation) { // Get wall kick data
            case 0 -> {
                if (r == 1) {
                    ArrayList<Integer[]> ZeroToR = tetromino.getWallKickData().getZeroToR();
                    for (Integer[] wallKickDir : ZeroToR) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> ZeroToL = tetromino.getWallKickData().getZeroToL();
                    for (Integer[] wallKickDir : ZeroToL) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation = 3;
                            return true;
                        }
                    }
                }
            }
            case 1 -> {
                if (r == 1) {
                    ArrayList<Integer[]> RToTwo = tetromino.getWallKickData().getRToTwo();
                    for (Integer[] wallKickDir : RToTwo) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> RToZero = tetromino.getWallKickData().getRToZero();
                    for (Integer[] wallKickDir : RToZero) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation--;
                            return true;
                        }
                    }
                }
            }
            case 2 -> {
                if (r == 1) {
                    ArrayList<Integer[]> TwoToL = tetromino.getWallKickData().getTwoToL();
                    for (Integer[] wallKickDir : TwoToL) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> TwoToR = tetromino.getWallKickData().getTwoToR();
                    for (Integer[] wallKickDir : TwoToR) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation--;
                            return true;
                        }
                    }
                }
            }
            case 3 -> {
                if (r == 1) {
                    ArrayList<Integer[]> LToZero = tetromino.getWallKickData().getLToZero();
                    for (Integer[] wallKickDir : LToZero) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation = 0;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> LToTwo = tetromino.getWallKickData().getLToTwo();
                    for (Integer[] wallKickDir : LToTwo) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1], board)) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            tetromino = currentPieceTemp.tetromino;
                            rotation--;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean hitObstacle(Board board) {
        for (int i = 0; i < tetromino.getShapeBoolean().length; i++) {
            for (int j = 0; j < tetromino.getShapeBoolean()[i].length; j++) {
                if (tetromino.getShapeBoolean()[i][j]) {
                    // Return false if failed to move because hit an obstacle
                    if (board.playfield[i+this.y][j+this.x].isPlaced()) return true;
                }
            }
        }
        return false;
    }

    public boolean hitObstacle(int x, int y, Board board) {
        for (int i = 0; i < tetromino.getShapeBoolean().length; i++) {
            for (int j = 0; j < tetromino.getShapeBoolean()[i].length; j++) {
                if (tetromino.getShapeBoolean()[i][j]) {
                    // Return false if failed to move because hit an obstacle
                    try {
                        if (board.playfield[y+i+this.y][x+j+this.x].isPlaced()) return true;
                    } catch (ArrayIndexOutOfBoundsException e) {return true;}
                }
            }
        }
        return false;
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
