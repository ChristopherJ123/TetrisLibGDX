package com.mygdx.game.tetromino.tetrominoes;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class STetromino extends Tetromino {

    public STetromino() {
        super("STetromino", GameConstants.ColorEnum.LIME);
        boolean[][] shape = {
                {false, true, true},
                {true, true, false},
                {false, false, false}
        };
        setShapeBoolean(shape);
        wallKickData = new WallKickData();
        wallKickData.JLSTZWallKickData();
        setWallKickData(wallKickData);
    }

    @Override
    public boolean isSpinSpecial(Board board, int locX, int locY, int rot) {
        int edgeCount = 0;
        switch (rot) {
            case 0 -> {
                if (board.getPlayfield()[locY+1][locX].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX+2].isPlaced()) edgeCount++;
            }
            case 1 -> {
                if (board.getPlayfield()[locY][locX+2].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX+1].isPlaced()) edgeCount++;
            }
            case 2 -> {
                if (board.getPlayfield()[locY+1][locX].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+3][locX+2].isPlaced()) edgeCount++;
            }
            case 3 -> {
                if (board.getPlayfield()[locY][locX+1].isPlaced()) edgeCount++;
                if (board.getPlayfield()[locY+2][locX].isPlaced()) edgeCount++;
            }
        }
        return edgeCount == 2;
    }
}
