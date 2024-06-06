package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class JTetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {true, false, false},
            {true, true, true},
            {false, false, false}
    };
    WallKickData wallKickData;

    public JTetromino() {
        tetromino = new Tetromino("JTetromino", Color.BLUE);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }
}
