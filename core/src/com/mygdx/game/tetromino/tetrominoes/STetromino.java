package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.Config;
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
        tetromino = new Tetromino("STetromino", Config.ColorEnum.LIME);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        tetromino.setWallKickData(wallKickData);
    }

    public static Tetromino get() {
        STetromino sTetromino = new STetromino();
        return sTetromino.tetromino;
    }
}
