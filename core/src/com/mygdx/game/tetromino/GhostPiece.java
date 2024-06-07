package com.mygdx.game.tetromino;

import com.mygdx.game.config.Config;

public class GhostPiece extends Tetromino {
    public GhostPiece(Tetromino tetromino) {
        super(tetromino);
        setColor(Config.ColorEnum.BLACK);

        for (Block[] blocks : tetromino.getShape()) {
            for (Block block : blocks) block.setSolid(false);
        }
    }
}
