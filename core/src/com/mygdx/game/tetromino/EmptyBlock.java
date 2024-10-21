package com.mygdx.game.tetromino;

import com.mygdx.game.config.GameConstants;

public class EmptyBlock extends Block {
    public EmptyBlock() {
        super();
        setColor(GameConstants.ColorEnum.BLACK);
    }
}
