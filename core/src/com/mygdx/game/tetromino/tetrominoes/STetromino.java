package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class STetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {false, true, true},
            {true, true, false},
            {false, false, false}
    };
    WallKickData wallKickData;

    public STetromino() {
        tetromino = new Tetromino("STetromino", Color.LIME);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }
}
