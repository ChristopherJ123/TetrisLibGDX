package com.mygdx.game.tetromino;

import com.mygdx.game.config.Config;

public class Tetromino {
    private String name;
    private Config.ColorEnum color;
    private Block[][] shape;
    private WallKickData wallKickData;

    public Tetromino(String name, Config.ColorEnum color) {
        this.name = name;
        this.color = color;
        this.shape = new Block[][]{{new Block()}};
        this.wallKickData = new WallKickData();
    }

    public Tetromino(String name, Config.ColorEnum color, Block[][] shape) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.wallKickData = new WallKickData();
    }

    public Tetromino(String name, Config.ColorEnum color, Block[][] shape, WallKickData wallKickData) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.wallKickData = wallKickData;
    }

    public Tetromino(Tetromino tetromino) {
        this.name = tetromino.name;
        this.color = tetromino.color;
        this.shape = tetromino.shape;
        this.wallKickData = tetromino.wallKickData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Config.ColorEnum getColor() {
        return color;
    }

    public void setColor(Config.ColorEnum color) {
        this.color = color;
    }

    public Block[][] getShape() {
        return shape;
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
