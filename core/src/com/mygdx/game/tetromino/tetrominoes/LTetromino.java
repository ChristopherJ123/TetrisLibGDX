package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.Config;
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
        tetromino = new Tetromino("LTetromino", Config.ColorEnum.ORANGE);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        tetromino.setWallKickData(wallKickData);
    }

    public static Tetromino get() {
        LTetromino lTetromino = new LTetromino();
        return lTetromino.tetromino;
    }
}
