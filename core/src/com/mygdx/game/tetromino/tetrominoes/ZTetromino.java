package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class ZTetromino extends Tetromino {

    public ZTetromino() {
        super("ZTetromino", GameConstants.ColorEnum.RED);
        boolean[][] shape = {
                {true, true, false},
                {false, true, true},
                {false, false, false}
        };
        setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
    }


    @Override
    public boolean isSpinSpecial(Board board, int locX, int locY, int rot) {
        int edgeCount = 0;
        switch (rot) {
            case 0 -> {
                System.out.println(locX);
                System.out.println(locY);
                System.out.println(rot);
                if (board.getPlayfield()[locY+1][locX+2].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX].isPlaced()) edgeCount++;
            }
            case 1 -> {
                if (board.getPlayfield()[locY][locX+1].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX+2].isPlaced()) edgeCount++;
            }
            case 2 -> {
                if (board.getPlayfield()[locY+1][locX+2].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+3][locX].isPlaced()) edgeCount++;
            }
            case 3 -> {
                if (board.getPlayfield()[locY][locX].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX+1].isPlaced()) edgeCount++;
            }
        }
        return edgeCount == 2;
    }
}
