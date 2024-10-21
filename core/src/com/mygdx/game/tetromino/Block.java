package com.mygdx.game.tetromino;

import com.mygdx.game.config.GameConstants;

public class Block {
    private GameConstants.ColorEnum color;
    private boolean isSolid;
    private boolean isPlaced;

    public Block() {
        this.color = GameConstants.ColorEnum.WHITE;
        this.isSolid = false;
        this.isPlaced = false;
    }

    public Block(GameConstants.ColorEnum color) {
        this.color = color;
        this.isSolid = false;
        this.isPlaced = false;
    }

    public Block(GameConstants.ColorEnum color, boolean isSolid, boolean isPlaced) {
        this.color = color;
        this.isSolid = isSolid;
        this.isPlaced = isPlaced;
    }

    public GameConstants.ColorEnum getColorEnum() {
        return color;
    }

    public void setColor(GameConstants.ColorEnum color) {
        this.color = color;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }
}
