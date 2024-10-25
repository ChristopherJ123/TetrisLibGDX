package com.mygdx.game.gamestate;

import com.mygdx.game.interfaces.Soundable;
import com.mygdx.game.interfaces.observer.Observer;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.tetrominoes.TTetromino;

public class Score implements Observer, Soundable {
    private int level;
    private int score;
    private int combo;
    private int b2b;
    private int b2bLineClear;


    public Score() {
        this.level = 1;
        this.score = 0;
        this.combo = -1;
        this.b2b = -1;
        this.b2bLineClear = 0; //later
    }

    public void addScoreOfLines(int linesCount) {
        switch (linesCount) {
            case 1 -> score += (100*level + (combo+1)*50);
            case 2 -> score += (300*level + (combo+1)*50);
            case 3 -> score += (500*level + (combo+1)*50);
            case 4 -> score += (800*level + ((combo+1)*combo)*50 + ((b2b+1) * 200));
        }
        b2b = linesCount==4? b2b + 1 : -1;

        if (linesCount == 0) {
            if (combo > 0) playSound("Tetrio/combobreak");
        } else {
            if (combo > 0 && combo <= 16) playSound("Tetrio/combo_" + combo);
            else if (combo > 16) playSound("Tetrio/combo_16");
        }

        combo = linesCount!=0 ? combo + 1 : -1;

        if (linesCount > 0 && linesCount < 4) playSound("Tetrio/clearline");
        else if (linesCount == 4) playSound("Tetrio/clearquad");
    }

    public void addScoreOfLines(int linesCount, Tetromino tetromino, boolean spinSpecial, boolean allClear) {
        if (tetromino instanceof TTetromino && spinSpecial) {
            switch (linesCount) {
                case 1 -> score += (800*level + combo*50) + ((b2b+1) * 200);
                case 2 -> score += (1200*level + combo*50) + ((b2b+1) * 200);
                case 3 -> score += (1600*level + combo*50) + ((b2b+1) * 200);
            }
            b2b++;
            if (b2b > 0) playSound("Tetrio/clearbtb");
            else playSound("Tetrio/clearspin");
        } else addScoreOfLines(linesCount);
        if (allClear) {
            switch (linesCount) {
                case 1 -> score += (800*level + combo*50);
                case 2 -> score += (1200*level + combo*50);
                case 3 -> score += (1800*level + combo*50);
                case 4 -> score += (2000*level + (combo*combo)*50 + (b2bLineClear * 3200));
            }
            playSound("Tetrio/allclear");
        }

        if (b2b == 2) playSound("Tetrio/btb_1");
        else if (b2b == 5) playSound("Tetrio/btb_2");
        else if (b2b >= 8 && b2b % 8 == 0) playSound("Tetrio/btb_3");
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

    @Override
    public void update(int lineCleared, Tetromino tetromino, boolean spinSpecial, boolean perfectClear) {
        addScoreOfLines(lineCleared, tetromino, spinSpecial, perfectClear);
        if (spinSpecial) System.out.println(tetromino.getName() + " spin!");
    }
}
