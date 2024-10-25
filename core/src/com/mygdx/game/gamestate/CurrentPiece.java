package com.mygdx.game.gamestate;

import com.mygdx.game.controls.Moveable;
import com.mygdx.game.interfaces.Soundable;
import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.tetrominoes.*;

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
        this.tetromino = currentPiece.getTetromino();
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
        switch (this.rotation) { // Get wall kick data
            case 0 -> {
                if (r == 1) {
                    ArrayList<Object[]> ZeroToR = tetromino.getWallKickData().getZeroToR();
                    for (Object[] wallKickDir : ZeroToR) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Object[]> ZeroToL = tetromino.getWallKickData().getZeroToL();
                    for (Object[] wallKickDir : ZeroToL) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation = 3;
                            return true;
                        }
                    }
                }
            }
            case 1 -> {
                if (r == 1) {
                    ArrayList<Object[]> RToTwo = tetromino.getWallKickData().getRToTwo();
                    for (Object[] wallKickDir : RToTwo) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Object[]> RToZero = tetromino.getWallKickData().getRToZero();
                    for (Object[] wallKickDir : RToZero) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation--;
                            return true;
                        }
                    }
                }
            }
            case 2 -> {
                if (r == 1) {
                    ArrayList<Object[]> TwoToL = tetromino.getWallKickData().getTwoToL();
                    for (Object[] wallKickDir : TwoToL) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation++;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Object[]> TwoToR = tetromino.getWallKickData().getTwoToR();
                    for (Object[] wallKickDir : TwoToR) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation--;
                            return true;
                        }
                    }
                }
            }
            case 3 -> {
                if (r == 1) {
                    ArrayList<Object[]> LToZero = tetromino.getWallKickData().getLToZero();
                    for (Object[] wallKickDir : LToZero) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation = 0;
                            return true;
                        }
                    }
                } else if (r == 3) {
                    ArrayList<Object[]> LToTwo = tetromino.getWallKickData().getLToTwo();
                    for (Object[] wallKickDir : LToTwo) {
                        if (!currentPieceTemp.hitObstacle((int)wallKickDir[0], (int)wallKickDir[1])) {
                            this.x += (int)wallKickDir[0];
                            this.y += (int)wallKickDir[1];
                            this.currentShape = currentPieceTemp.currentShape;
                            this.rotation--;
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
        if (this.tetromino instanceof ITetromino) return new ITetromino();
        else if (this.tetromino instanceof JTetromino) return new JTetromino();
        else if (this.tetromino instanceof LTetromino) return new LTetromino();
        else if (this.tetromino instanceof OTetromino) return new OTetromino();
        else if (this.tetromino instanceof STetromino) return new STetromino();
        else if (this.tetromino instanceof TTetromino) return new TTetromino();
        else if (this.tetromino instanceof ZTetromino) return new ZTetromino();
        return null;
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
        playSound("move");
        return changeBy(-1, 0);
    }

    @Override
    public boolean moveRight() {
        playSound("move");
        return changeBy(1, 0);
    }

    @Override
    public boolean moveDown() {
        playSound("move");
        return changeBy(0, -1);
    }

    @Override
    public boolean rotateLeft() {
        if (rotateBy(-1)) {
            if (this.tetromino.isSpinSpecial(this.board, this.x, this.y, this.rotation)) playSound("Tetrio/spin");
            else playSound("Tetrio/rotate");
            return true;
        }
        return false;
    }

    @Override
    public boolean rotateRight() {
        if (rotateBy(1)) {
            if (this.tetromino.isSpinSpecial(this.board, this.x, this.y, this.rotation)) playSound("Tetrio/spin");
            else playSound("Tetrio/rotate");
            return true;
        }
        return false;
    }

    @Override
    public boolean rotate180() {
        if (rotateBy(2)) {
            if (this.tetromino.isSpinSpecial(this.board, this.x, this.y, this.rotation)) playSound("Tetrio/spin");
            else playSound("Tetrio/rotate");
            return true;
        }
        return false;
    }
}
