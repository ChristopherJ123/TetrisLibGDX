package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
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
        tetromino = new Tetromino("JTetromino", GameConstants.ColorEnum.BLUE);
        tetromino.setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        tetromino.setWallKickData(wallKickData);
    }

    public static Tetromino get() {
        JTetromino jTetromino = new JTetromino();
        return jTetromino.tetromino;
    }
}
