package com.mygdx.game.gamestate;

import com.mygdx.game.config.GameConstants;
import com.mygdx.game.interfaces.Soundable;
import com.mygdx.game.interfaces.observer.Observer;
import com.mygdx.game.interfaces.observer.Subject;
import com.mygdx.game.tetromino.Block;
import com.mygdx.game.tetromino.EmptyBlock;

import java.util.ArrayList;
import java.util.List;

public class Board implements Subject, Soundable {
    private final List<Observer> observers;
    public Block[][] playfield;
    private CurrentPiece lastPlacedTetromino;

    public Board() {
        this.observers = new ArrayList<>();
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
        this.lastPlacedTetromino = currentPiece;
    }

    public int clearLineIfAny() {
        int lineClearCount = 0;
        for (int i = 0; i < this.playfield.length; i++) {
            boolean lineClear = true;
            for (int j = 0; j < this.playfield[i].length; j++) {
                if (!this.playfield[i][j].isPlaced()) {
                    lineClear = false;
                    break;
                }
            }
            if (lineClear) {
                lineClearCount++;
                for (int j = i; j < this.playfield.length - 1; j++) {
                    for (int k = 0; k < this.playfield[j].length; k++) {
                        this.playfield[j][k] = this.playfield[j+1][k];
                    }
                }
                i--;
            }
        }
        System.out.println(lineClearCount!=0);
        // todo fix ben
//        if (lineClearCount > 0) {
//            if (lineClearCount == 4) {
//                playSound("crush", "wav");
//            } else {
//                switch (getCombo()) {
//                    case 0: playSound("combo1", "wav"); setCombo(1); break;
//                    case 1: playSound("combo2", "wav"); setCombo(2); break;
//                    case 2: playSound("combo3", "wav"); setCombo(3); break;
//                    case 3: playSound("combo4", "wav"); setCombo(4); break;
//                    case 4: playSound("combo5", "wav"); setCombo(5); break;
//                    case 5: playSound("combo6", "wav"); setCombo(6); break;
//                    case 6: playSound("combo7", "wav"); setCombo(7); break;
//                    case 7: playSound("combo8", "wav"); setCombo(0); break;
//                    default: break;
//                }
//            }
//        }
        return lineClearCount;
    }

    public boolean boardIsEmpty() {
        for (int i = 0; i < playfield.length; i++) {
            for (int j = 0; j < playfield[i].length; j++) {
                if (playfield[i][j].isPlaced()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Block[][] getPlayfield() {
        return playfield;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            int linesCleared = clearLineIfAny();
            o.update(
                    linesCleared,
                    lastPlacedTetromino.getTetromino(),
                    lastPlacedTetromino.getTetromino().isSpinSpecial(
                            this,
                            lastPlacedTetromino.getX(),
                            lastPlacedTetromino.getY(),
                            lastPlacedTetromino.getRotation()
                    ),
                    boardIsEmpty()
            );
        }
    }
}
