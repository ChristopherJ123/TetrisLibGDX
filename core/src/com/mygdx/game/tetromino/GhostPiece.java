package com.mygdx.game.tetromino;

import com.badlogic.gdx.graphics.Color;

public class GhostPiece extends Tetromino {
    public GhostPiece(Tetromino tetromino) {
        super(tetromino);
        setColor(Color.BLACK);

        for (Block[] blocks : tetromino.getShape()) {
            for (Block block : blocks) block.setSolid(false);
        }
    }
}
