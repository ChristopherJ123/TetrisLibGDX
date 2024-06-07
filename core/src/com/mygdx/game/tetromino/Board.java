package com.mygdx.game.tetromino;

import com.mygdx.game.config.Config;

public class Board {
    public Block[][] playfield;

    public Board() {
        playfield = new Block[Config.BOARD_HEIGHT + Config.BOARD_BUFFER_ZONE][Config.BOARD_WIDTH];

        for (Block[] blocks : playfield) {
            for (Block block : blocks) {
                block = new Block();
            }
        }
    }
}
