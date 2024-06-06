package com.mygdx.game.tetromino;

import com.badlogic.gdx.graphics.Color;

public class Tetromino {

    private String name;
    private Color color;
    private boolean[][] shape;
    private WallKickData wallKickData;

    public Tetromino(String name, Color color) {
        this.name = name;
        this.color = color;
        this.shape = new boolean[][]{{true}};
        this.wallKickData = new WallKickData();
    }

    public Tetromino(String name, Color color, boolean[][] shape) {
        this.name = name;
        this.color = color;
        this.shape = shape;
    }

    public Tetromino(String name, Color color, boolean[][] shape, WallKickData wallKickData) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.wallKickData = wallKickData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
    }

    public WallKickData getWallKickData() {
        return wallKickData;
    }

    public void setWallKickData(WallKickData wallKickData) {
        this.wallKickData = wallKickData;
    }
}
