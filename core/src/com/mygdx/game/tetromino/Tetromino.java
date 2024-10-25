package com.mygdx.game.tetromino;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;

import java.util.Arrays;

public abstract class Tetromino {
    //todo make abstract
    private String name;
    private GameConstants.ColorEnum color;
    private Block[][] shape;
    protected WallKickData wallKickData;

    public Tetromino(String name, GameConstants.ColorEnum color) {
        this.name = name;
        this.color = color;
        this.shape = new Block[][]{{new Block()}};
        this.wallKickData = new WallKickData();
    }

    public Tetromino(String name, GameConstants.ColorEnum color, Block[][] shape) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.wallKickData = new WallKickData();
    }

    public Tetromino(String name, GameConstants.ColorEnum color, Block[][] shape, WallKickData wallKickData) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.wallKickData = wallKickData;
    }

    public Tetromino(Tetromino tetromino) {
        this.name = tetromino.name;
        this.color = tetromino.color;
        this.shape = new Block[tetromino.shape.length][];
        for (int i = 0; i < tetromino.shape.length; i++) {
            this.shape[i] = Arrays.copyOf(tetromino.shape[i], tetromino.shape[i].length);
        }
        this.wallKickData = tetromino.wallKickData;
    }

    public abstract boolean isSpinSpecial(Board board, int locX, int locY, int rot);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameConstants.ColorEnum getColor() {
        return color;
    }

    public void setColor(GameConstants.ColorEnum color) {
        this.color = color;
    }

    public Block[][] getShape() {
        Block[][] newShape = new Block[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                newShape[i][j] = new Block(shape[i][j].getColorEnum(), shape[i][j].isSolid(), shape[i][j].isPlaced());
            }
        }
        return newShape;
    }

    public void setShape(Block[][] shape) {
        this.shape = shape;
    }

    public boolean[][] getShapeBoolean() {
        boolean[][] shapeBoolean = new boolean[this.shape.length][this.shape[0].length];
        for (int i = 0; i < shapeBoolean.length; i++) {
            for (int j = 0; j < shapeBoolean[i].length; j++) {
                shapeBoolean[i][j] = this.shape[i][j].isSolid();
            }
        }
        return shapeBoolean;
    }

    public void setShapeBoolean(boolean[][] shape) {
        int numRows = shape.length;
        int numCols = shape[0].length;
        Block[][] shapeBlocks = new Block[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                shapeBlocks[numRows - 1 - i][j] = shape[i][j] ? new Block(this.color, true, false) : new EmptyBlock();
            }
        }

        this.shape = shapeBlocks;
    }

    public WallKickData getWallKickData() {
        return wallKickData;
    }

    public void setWallKickData(WallKickData wallKickData) {
        this.wallKickData = wallKickData;
    }
}
