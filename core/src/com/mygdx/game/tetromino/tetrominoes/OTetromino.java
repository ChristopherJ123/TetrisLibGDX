package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class OTetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {true, true},
            {true, true}
    };
    WallKickData wallKickData;

    public OTetromino() {
        tetromino = new Tetromino("OTetromino", Color.YELLOW);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();
    }
}
