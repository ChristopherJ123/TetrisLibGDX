package com.mygdx.game.gamestate;

import com.mygdx.game.controls.Moveable;
import com.mygdx.game.controls.Soundable;
import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.Tetromino;

import java.util.ArrayList;

public class CurrentPiece implements Moveable, Soundable {
    private Board board;
    private Tetromino tetromino;
    private Block[][] currentShape;
    private int x;
    private int y;
    private int rotation;

    public CurrentPiece(Board board, Tetromino tetromino, int x, int y) {
        this.board = board;
        this.tetromino = tetromino;
        this.currentShape = tetromino.getShape();
        this.x = x;
        this.y = y;
        this.rotation = 0;
    }

    public CurrentPiece(CurrentPiece currentPiece) {
        this.board = currentPiece.board;
        this.tetromino = new Tetromino(currentPiece.getTetromino());
        this.currentShape = currentPiece.currentShape;
        this.x = currentPiece.getX();
        this.y = currentPiece.getY();
        this.rotation = currentPiece.getRotation();
    }

    public boolean changeBy(int x, int y) {
        if (!hitObstacle(x, y)) {
            this.x += x;
            this.y += y;
            return true; // Return true if successfully moved
        } else if (hitWall(x, y)) {
            playSound("hit", "wav");
        }
        return false;
    }

    public boolean rotateBy(int r) {
        playSound("switch", "wav");
        CurrentPiece currentPieceTemp = new CurrentPiece(this);
        int width = currentShape.length;
        int height = currentShape[0].length;
        Block[][] temp = new Block[height][width];
        while (r < 0) r += 4;
        switch (r % 4) { // Rotate the shape
            case 1 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        temp[height-1-j][i] = new Block(currentPieceTemp.currentShape[i][j].getColorEnum(),
                                currentPieceTemp.currentShape[i][j].isSolid(),
                                currentPieceTemp.currentShape[i][j].isPlaced()
                        );
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        temp[width-1-i][height-1-j] = new Block(currentPieceTemp.currentShape[i][j].getColorEnum(),
                                currentPieceTemp.currentShape[i][j].isSolid(),
                                currentPieceTemp.currentShape[i][j].isPlaced()
                        );
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        temp[j][width-1-i] = new Block(currentPieceTemp.currentShape[i][j].getColorEnum(),
                                currentPieceTemp.currentShape[i][j].isSolid(),
                                currentPieceTemp.currentShape[i][j].isPlaced()
                        );
                    }
                }
            }
            case 0 -> {
                return true;
            }
        }
        currentPieceTemp.currentShape = temp;
        switch (rotation) { // Get wall kick data
            case 0 -> {
                if (r == 1) {
                    ArrayList<Integer[]> ZeroToR = tetromino.getWallKickData().getZeroToR();
                    for (Integer[] wallKickDir : ZeroToR) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> ZeroToL = tetromino.getWallKickData().getZeroToL();
                    for (Integer[] wallKickDir : ZeroToL) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
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
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> RToZero = tetromino.getWallKickData().getRToZero();
                    for (Integer[] wallKickDir : RToZero) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
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
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
                            rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> TwoToR = tetromino.getWallKickData().getTwoToR();
                    for (Integer[] wallKickDir : TwoToR) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
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
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
                            rotation = 0;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Integer[]> LToTwo = tetromino.getWallKickData().getLToTwo();
                    for (Integer[] wallKickDir : LToTwo) {
                        if (!currentPieceTemp.hitObstacle(wallKickDir[0], wallKickDir[1])) {
                            this.x += wallKickDir[0];
                            this.y += wallKickDir[1];
                            currentShape = currentPieceTemp.currentShape;
                            rotation--;
                            return true;
                        }
                    }
                }
            }
        }
        if (r == 2) {
            if (currentPieceTemp.hitObstacle()) {
                if (currentPieceTemp.hitWall(x, y)) {
                    playSound("hit", "wav");
                }
                    return false;
                }
            else {
                currentShape = currentPieceTemp.currentShape;
            }
        }
        return false;
    }

    public boolean hitObstacle() {
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j].isSolid()) {
                    // Return false if failed to move because hit an obstacle
                    try {
                        if (board.playfield[i + this.y][j + this.x].isPlaced()) return true;
                    } catch (ArrayIndexOutOfBoundsException e) {return true;}
                }
            }
        }
        return false;
    }

    public boolean hitObstacle(int x, int y) {
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j].isSolid()) {
                    // Return false if failed to move because hit an obstacle
                    try {
                        if (board.playfield[y+i+this.y][x+j+this.x].isPlaced())
                            return true;
                    } catch (ArrayIndexOutOfBoundsException e) {return true;}
                }
            }
        }
        return false;
    }

    public boolean hitWall(int x, int y) {
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                if (currentShape[i][j].isSolid()) {
                    // Check if the block is hitting the left or right wall
                    if (x + j + this.x < 0 || x + j + this.x >= board.playfield[0].length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getPlacementY() {
        int y = -1;
        while (!hitObstacle(0, y)) y--;
        return y + this.y;
    }

    public Tetromino getTetromino() {
        return new Tetromino(this.tetromino);
    }

    public Block[][] getCurrentShape() {
        Block[][] temp = new Block[currentShape.length][currentShape[0].length];
        for (int i = 0; i < currentShape.length; i++) {
            for (int j = 0; j < currentShape[i].length; j++) {
                temp[i][j] = new Block(currentShape[i][j].getColorEnum(),
                        currentShape[i][j].isSolid(),
                        currentShape[i][j].isPlaced()
                );
            }
        }
        return temp;
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public boolean moveLeft() {
        playSound("move", "wav");
        return changeBy(-1, 0);
    }

    @Override
    public boolean moveRight() {
        playSound("move", "wav");
        return changeBy(1, 0);
    }

    @Override
    public boolean moveDown() {
        playSound("move", "wav");
        return changeBy(0, -1);
    }

    @Override
    public boolean rotateLeft() {
        return rotateBy(-1);
    }

    @Override
    public boolean rotateRight() {
        return rotateBy(1);
    }

    @Override
    public boolean rotate180() {
        return rotateBy(2);
    }
}
