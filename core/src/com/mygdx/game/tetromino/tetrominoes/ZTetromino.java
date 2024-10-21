package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
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
        tetromino = new Tetromino("ZTetromino", GameConstants.ColorEnum.RED);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        tetromino.setWallKickData(wallKickData);
    }

    public static Tetromino get() {
        ZTetromino zTetromino = new ZTetromino();
        return zTetromino.tetromino;
    }
}
