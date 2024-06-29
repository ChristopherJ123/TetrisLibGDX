package com.mygdx.game.gamestate;

import com.mygdx.game.tetromino.Tetromino;

public class HoldPiece {
    private Tetromino tetromino = null;
    private boolean recentlyUsedHold;

    public HoldPiece() {
    }

    public Tetromino set(Tetromino tetromino) {
        Tetromino currentHoldingTetromino = this.tetromino;
        this.tetromino = tetromino;
        return currentHoldingTetromino;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public boolean isRecentlyUsedHold() {
        return recentlyUsedHold;
    }

    public void setRecentlyUsedHold(boolean recentlyUsedHold) {
        this.recentlyUsedHold = recentlyUsedHold;
    }
}
