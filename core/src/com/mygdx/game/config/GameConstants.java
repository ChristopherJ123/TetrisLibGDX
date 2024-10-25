package com.mygdx.game.config;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.tetrominoes.*;

public class GameConstants {
    public static final int BOARD_BUFFER_ZONE = 20;
    public static final int BOARD_HEIGHT = 20; // Maksimum height jumlah balok
    public static final int BOARD_WIDTH = 10; // Maksimum width jumlah balok
    public static float DAS = 0.4f; // Initial movement delay
    public static float ARR = 0.1f; // Initial repeat delay
    public static float SDF = 5f; // Soft Drop Factor
    public static final float AUTOMATIC_DROP_DELAY = 1f;

    public enum Tetrominoes {
        ITETROMINO(new ITetromino()),
        JTETROMINO(new JTetromino()),
        LTETROMINO(new LTetromino()),
        OTETROMINO(new OTetromino()),
        STETROMINO(new STetromino()),
        TTETROMINO(new TTetromino()),
        ZTETROMINO(new ZTetromino());

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
