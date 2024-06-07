package com.mygdx.game.tetromino;

import com.badlogic.gdx.graphics.Color;

public class Block {
    private Color color;
    private boolean isSolid;
    private boolean isPlaced;

    public Block() {
        color = Color.WHITE;
        isSolid = false;
        isPlaced = false;
    }

    public Block(boolean isSolid) {
        this.isSolid = isSolid;
        this.isPlaced = false;
    }

    public Block(boolean isSolid, boolean isPlaced) {
        this.isSolid = isSolid;
        this.isPlaced = isPlaced;
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
