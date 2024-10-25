package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class JTetromino extends Tetromino{

    public JTetromino() {
        super("JTetromino", GameConstants.ColorEnum.BLUE);
        boolean[][] shape = {
                {true, false, false},
                {true, true, true},
                {false, false, false}
        };
        setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }


    @Override
    public boolean isSpinSpecial(Board board, int locX, int locY, int rot) {
        int fiveCornersCheckCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (board.getPlayfield()[locY+j][locX+i].isPlaced()) fiveCornersCheckCount++;
                } catch (Exception e) {fiveCornersCheckCount++;}
            }
        }
        return fiveCornersCheckCount >= 5;
    }
}
