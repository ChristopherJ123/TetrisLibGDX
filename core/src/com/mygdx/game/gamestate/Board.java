package com.mygdx.game.gamestate;

import com.mygdx.game.config.Config;
import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.EmptyBlock;

public class Board {
    public Block[][] playfield;

    public Board() {
        playfield = new Block[Config.BOARD_HEIGHT + Config.BOARD_BUFFER_ZONE][Config.BOARD_WIDTH];

        for (int i = 0; i < playfield.length; i++) {
            for (int j = 0; j < playfield[i].length; j++) {
                playfield[i][j] = new EmptyBlock();
            }
        }
    }

    public void addCurrentPiece(CurrentPiece currentPiece) {
        for (int i = 0; i < currentPiece.getTetromino().getShapeBoolean().length; i++) {
            for (int j = 0; j < currentPiece.getTetromino().getShapeBoolean()[i].length; j++) {
                if (currentPiece.getTetromino().getShapeBoolean()[i][j]) {
                    playfield[currentPiece.getY()+i][currentPiece.getX()+j] = new Block(currentPiece.getTetromino().getColor(), true, true);
                }
            }
        }
    }

    public Block[][] getPlayfield() {
        return playfield;
    }

    public void setPlayfield(Block[][] playfield) {
        this.playfield = playfield;
    }
}
