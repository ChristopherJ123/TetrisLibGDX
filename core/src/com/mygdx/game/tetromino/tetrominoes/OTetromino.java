package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class OTetromino extends Tetromino {

    public OTetromino() {
        super("OTetromino", GameConstants.ColorEnum.YELLOW);
        boolean[][] shape = {
                {true, true},
                {true, true}
        };
        setShapeBoolean(shape);
        wallKickData = new WallKickData();
    }


    @Override
    public boolean isSpinSpecial(Board board, int locX, int locY, int rot) {
        return false;
    }
}
