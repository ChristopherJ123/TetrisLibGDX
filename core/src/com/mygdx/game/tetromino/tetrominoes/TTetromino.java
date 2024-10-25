package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class TTetromino extends Tetromino{

    public TTetromino() {
        super("TTetromino", GameConstants.ColorEnum.MAGENTA);
        boolean[][] shape = {
                {false, true, false},
                {true, true, true},
                {false, false, false}
        };
        setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }

    @Override
    public boolean isSpinSpecial(Board board, int locX, int locY, int rot) {
        int threeCornersCheckCount = 0;
        try {
            if (board.getPlayfield()[locY][locX].isPlaced()) threeCornersCheckCount++;
        } catch (Exception e) {threeCornersCheckCount++;}
        try {
            if (board.getPlayfield()[locY][locX+2].isPlaced()) threeCornersCheckCount++;
        } catch (Exception e) {threeCornersCheckCount++;}
        try {
            if (board.getPlayfield()[locY+2][locX].isPlaced()) threeCornersCheckCount++;
        } catch (Exception e) {threeCornersCheckCount++;}
        try {
            if (board.getPlayfield()[locY+2][locX+2].isPlaced()) threeCornersCheckCount++;
        } catch (Exception e) {threeCornersCheckCount++;}
        return threeCornersCheckCount >= 3;
    }
}
