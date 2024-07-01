package com.mygdx.game.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.controls.Soundable;

public class Score implements Soundable {
    private int level;
    private int score;
    private int combo;
    private int b2b;
    private int b2bLineClear;


    public Score() {
        this.level = 1;
        this.score = 0;
        this.combo = 0;
        this.b2b = 0;
        this.b2bLineClear = 0;
    }

    public void addScoreOfLines(int linesCount) {
        switch (linesCount) {
            case 1 -> score += (100*level + combo*50);
            case 2 -> score += (300*level + combo*50);
            case 3 -> score += (500*level + combo*50);
            case 4 -> score += (800*level + (combo*combo)*50 + (b2b * 200));
        }
    }

    public void addScoreOfLines(int linesCount, boolean tSpin, boolean allClear) {
        if (tSpin) {
            switch (linesCount) {
                case 1 -> score += (800*level + combo*50);
                case 2 -> score += (1200*level + combo*50);
                case 3 -> score += (1600*level + combo*50);
            }
        }
        if (allClear) {
            switch (linesCount) {
                case 1 -> score += (800*level + combo*50);
                case 2 -> score += (1200*level + combo*50);
                case 3 -> score += (1800*level + combo*50);
                case 4 -> score += (2000*level + (combo*combo)*50 + (b2bLineClear * 3200));
            }
        }
    }

    public boolean clearLineIfAny(Board board) {
        int lineClearCount = 0;
        for (int i = 0; i < board.playfield.length; i++) {
            boolean lineClear = true;
            for (int j = 0; j < board.playfield[i].length; j++) {
                if (!board.playfield[i][j].isPlaced()) {
                    lineClear = false;
                    break;
                }
            }
            if (lineClear) {
                lineClearCount++;
                for (int j = i; j < board.playfield.length - 1; j++) {
                    for (int k = 0; k < board.playfield[j].length; k++) {
                        board.playfield[j][k] = board.playfield[j+1][k];
                    }
                }
                i--;
            }
        }

        if (lineClearCount > 0) {
            if (lineClearCount == 4) {
                playSound("4Rows", "wav");
            } else {
                switch (getCombo()) {
                    case 0: playSound("combo1", "wav"); setCombo(1); break;
                    case 1: playSound("combo2", "wav"); setCombo(2); break;
                    case 2: playSound("combo3", "wav"); setCombo(3); break;
                    case 3: playSound("combo4", "wav"); setCombo(4); break;
                    case 4: playSound("combo5", "wav"); setCombo(5); break;
                    case 5: playSound("combo6", "wav"); setCombo(6); break;
                    case 6: playSound("combo7", "wav"); setCombo(7); break;
                    case 7: playSound("combo8", "wav"); setCombo(0); break;
                    default: break;
                }
            }
        }

        addScoreOfLines(lineClearCount);
        return lineClearCount != 0;
    }

    public boolean isAllClear(Board board) {
        for (int i = 0; i < board.playfield.length; i++) {
            for (int j = 0; j < board.playfield[i].length; j++) {
                if (board.playfield[i][j].isPlaced()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addScore(int score) {
        score += score;
    }

    public void addCombo() {
        combo++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getB2b() {
        return b2b;
    }

    public void setB2b(int b2b) {
        this.b2b = b2b;
    }

    public int getB2bLineClear() {
        return b2bLineClear;
    }

    public void setB2bLineClear(int b2bLineClear) {
        this.b2bLineClear = b2bLineClear;
    }
}
