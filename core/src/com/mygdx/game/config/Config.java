package com.mygdx.game.config;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.tetrominoes.*;

public class Config {
    public static final int BOARD_BUFFER_ZONE = 20;
    public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;
    public static final float DAS = 0.2f; // Initial movement delay
    public static final float ARR = 0.001f; // Initial repeat delay
    public static final float AUTO_DROP_DELAY = 1f; // Automatic Drop delay

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
        WHITE(Color.WHITE),
        PLACEMENT(Color.GRAY);

        private final Color color;

        ColorEnum(Color color) {
            this.color = color;
        }

        public Color getColor() {return color;}
    }
}
