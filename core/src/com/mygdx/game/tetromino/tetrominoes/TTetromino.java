package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class TTetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {false, true, false},
            {true, true, true},
            {false, false, false}
    };
    WallKickData wallKickData;

    public TTetromino() {
        tetromino = new Tetromino("TTetromino", GameConstants.ColorEnum.MAGENTA);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        tetromino.setWallKickData(wallKickData);
    }

    public static Tetromino get() {
        TTetromino tTetromino = new TTetromino();
        return tTetromino.tetromino;
    }
}
