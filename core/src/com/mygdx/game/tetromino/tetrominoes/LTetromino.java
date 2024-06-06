package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class LTetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {false, false, true},
            {true, true, true},
            {false, false, false}
    };
    WallKickData wallKickData;

    public LTetromino() {
        tetromino = new Tetromino("LTetromino", Color.ORANGE);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }
}
