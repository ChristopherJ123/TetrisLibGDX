package com.mygdx.game.tetromino;

import com.mygdx.game.config.Config;

public class EmptyBlock extends Block {
    public EmptyBlock() {
        super();
        setColor(Config.ColorEnum.BLACK);
    }
}
