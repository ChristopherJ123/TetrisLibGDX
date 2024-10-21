package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
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
        tetromino = new Tetromino("OTetromino", GameConstants.ColorEnum.YELLOW);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
    }

    public static Tetromino get() {
        OTetromino oTetromino = new OTetromino();
        return oTetromino.tetromino;
    }
}
