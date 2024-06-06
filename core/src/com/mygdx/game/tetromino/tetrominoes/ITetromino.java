package com.mygdx.game.tetromino.tetrominoes;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.tetromino.Tetromino;
import com.mygdx.game.tetromino.WallKickData;

public class ITetromino {
    Tetromino tetromino;
    boolean[][] shape = {
            {false, false, false, false},
            {true, true, true, true},
            {false, false, false, false},
            {false, false, false, false}
    };
    WallKickData wallKickData;

    public ITetromino() {
        tetromino = new Tetromino("JTetromino", Color.BLUE);
        tetromino.setShape(shape);
        wallKickData = new WallKickData();

        wallKickData.addWallKickData("0->R", 0, 0);
        wallKickData.addWallKickData("0->R", -2, 0);
        wallKickData.addWallKickData("0->R", 1, 0);
        wallKickData.addWallKickData("0->R", 1, 2);
        wallKickData.addWallKickData("0->R", -2, -1);
        wallKickData.addWallKickData("R->0", 0, 0);
        wallKickData.addWallKickData("R->0", 2, 0);
        wallKickData.addWallKickData("R->0", -1, 0);
        wallKickData.addWallKickData("R->0", 2, 1);
        wallKickData.addWallKickData("R->0", -1, -2);
        wallKickData.addWallKickData("R->2", 0, 0);
        wallKickData.addWallKickData("R->2", -1, 0);
        wallKickData.addWallKickData("R->2", 2, 0);
        wallKickData.addWallKickData("R->2", -1, 2);
        wallKickData.addWallKickData("R->2", 2, -1);
        wallKickData.addWallKickData("2->R", 0, 0);
        wallKickData.addWallKickData("2->R", -2, 0);
        wallKickData.addWallKickData("2->R", 1, 0);
        wallKickData.addWallKickData("2->R", -2, 1);
        wallKickData.addWallKickData("2->R", 1, -1);
        wallKickData.addWallKickData("2->L", 0, 0);
        wallKickData.addWallKickData("2->L", 2, 0);
        wallKickData.addWallKickData("2->L", -1, 0);
        wallKickData.addWallKickData("2->L", 2, 1);
        wallKickData.addWallKickData("2->L", -1, -1);
        wallKickData.addWallKickData("L->2", 0, 0);
        wallKickData.addWallKickData("L->2", 1, 0);
        wallKickData.addWallKickData("L->2", -2, 0);
        wallKickData.addWallKickData("L->2", 1, 2);
        wallKickData.addWallKickData("L->2", -2, -1);
        wallKickData.addWallKickData("L->0", 0, 0);
        wallKickData.addWallKickData("L->0", -2, 0);
        wallKickData.addWallKickData("L->0", 1, 0);
        wallKickData.addWallKickData("L->0", -2, 1);
        wallKickData.addWallKickData("L->0", 1, -2);
        wallKickData.addWallKickData("0->L", 0, 0);
        wallKickData.addWallKickData("0->L", 2, 0);
        wallKickData.addWallKickData("0->L", -1, 0);
        wallKickData.addWallKickData("0->L", -1, 2);
        wallKickData.addWallKickData("0->L", 2, -1);
    }
}
