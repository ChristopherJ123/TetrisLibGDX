package com.mygdx.game.tetromino;

import com.mygdx.game.config.Config;

public class Block {
    private Config.ColorEnum color;
    private boolean isSolid;
    private boolean isPlaced;

    public Block() {
        this.color = Config.ColorEnum.WHITE;
        this.isSolid = false;
        this.isPlaced = false;
    }

    public Block(Config.ColorEnum color) {
        this.color = color;
        this.isSolid = false;
        this.isPlaced = false;
    }

    public Block(Config.ColorEnum color, boolean isSolid, boolean isPlaced) {
        this.color = color;
        this.isSolid = isSolid;
        this.isPlaced = isPlaced;
    }

    public Config.ColorEnum getColorEnum() {
        return color;
    }

    public void setColor(Config.ColorEnum color) {
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
