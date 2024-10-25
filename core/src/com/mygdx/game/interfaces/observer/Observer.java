package com.mygdx.game.interfaces.observer;

import com.mygdx.game.tetromino.Tetromino;

public interface Observer {
    void update(int lineCleared, Tetromino tetromino, boolean spinSpecial, boolean perfectClear);
}
