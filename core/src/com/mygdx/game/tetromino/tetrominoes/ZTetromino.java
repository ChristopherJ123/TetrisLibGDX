package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class ZTetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {true, true, false},
            {false, true, true},
            {false, false, false}
    };
    WallKickData wallKickData;

    public ZTetromino() {
        tetromino = new Tetromino("ZTetromino", Color.RED);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }
}
