package com.mygdx.game.config;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.tetrominoes.*;

public class Config {
    public static final int BOARD_BUFFER_ZONE = 20;
    public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;

    public enum Tetrominoes {
        ITETROMINO(ITetromino.get()),
        JTETROMINO(JTetromino.get()),
        LTETROMINO(LTetromino.get()),
        OTETROMINO(OTetromino.get()),
        STETROMINO(STetromino.get()),
        TTETROMINO(TTetromino.get()),
        ZTETROMINO(ZTetromino.get());

        private final Tetromino type;

        Tetrominoes(Tetromino type) {
            this.type = type;
        }

        public Tetromino getType() {
            return type;
        }
    }

    public enum ColorEnum {
        RED(Color.RED),
        ORANGE(Color.ORANGE),
        YELLOW(Color.YELLOW),
        LIME(Color.LIME),
        TEAL(Color.TEAL),
        BLUE(Color.BLUE),
        MAGENTA(Color.MAGENTA),
        BLACK(Color.BLACK),
        WHITE(Color.WHITE);

        private final Color color;

        ColorEnum(Color color) {
            this.color = color;
        }

        public Color getColorEnum() {return color;}
    }
}
