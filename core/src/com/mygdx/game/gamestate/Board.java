package com.mygdx.game.gamestate;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.controls.Soundable;
import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.EmptyBlock;

public class Board implements Soundable {
    public Block[][] playfield;

    public Board() {
        playfield = new Block[GameConstants.BOARD_HEIGHT + GameConstants.BOARD_BUFFER_ZONE][GameConstants.BOARD_WIDTH];

        for (int i = 0; i < playfield.length; i++) {
            for (int j = 0; j < playfield[i].length; j++) {
                playfield[i][j] = new EmptyBlock();
            }
        }
    }

    public void placeCurrentPiece(CurrentPiece currentPiece) {
        playSound("blockPlacedSound", "wav");
        for (int i = 0; i < currentPiece.getCurrentShape().length; i++) {
            for (int j = 0; j < currentPiece.getCurrentShape()[i].length; j++) {
                if (currentPiece.getCurrentShape()[i][j].isSolid()) {
                    playfield[currentPiece.getY()+i][currentPiece.getX()+j] = new Block(currentPiece.getTetromino().getColor(), true, true);
                }
            }
        }
    }

    public Block[][] getPlayfield() {
        return playfield;
    }
}
