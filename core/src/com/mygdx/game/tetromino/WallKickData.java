package com.mygdx.game.tetromino;

import java.util.ArrayList;

public class WallKickData {
    private ArrayList<Integer[]> ZeroToR;
    private ArrayList<Integer[]> RToZero;
    private ArrayList<Integer[]> RToTwo;
    private ArrayList<Integer[]> TwoToR;
    private ArrayList<Integer[]> TwoToL;
    private ArrayList<Integer[]> LToTwo;
    private ArrayList<Integer[]> LToZero;
    private ArrayList<Integer[]> ZeroToL;

    public WallKickData() {

    }

    public boolean addWallKickData(String type, int x, int y) {
        switch (type) {
            case "0->R" -> ZeroToR.add(new Integer[]{x, y});
            case "R->0" -> RToZero.add(new Integer[]{x, y});
            case "R->2" -> RToTwo.add(new Integer[]{x, y});
            case "2->R" -> TwoToR.add(new Integer[]{x, y});
            case "2->L" -> TwoToL.add(new Integer[]{x, y});
            case "L->2" -> LToTwo.add(new Integer[]{x, y});
            case "L->0" -> LToZero.add(new Integer[]{x, y});
            case "0->L" -> ZeroToL.add(new Integer[]{x, y});
            default -> {
                return false;
            }
        }
        return true;
    }

    public void JLSTZWallKickData() {
        addWallKickData("0->R", 0, 0);
        addWallKickData("0->R", -1, 0);
        addWallKickData("0->R", -1, 1);
        addWallKickData("0->R", 0, -2);
        addWallKickData("0->R", -1, -2);
        addWallKickData("R->0", 0, 0);
        addWallKickData("R->0", 1, 0);
        addWallKickData("R->0", 1, -1);
        addWallKickData("R->0", 0, 2);
        addWallKickData("R->0", 1, 2);
        addWallKickData("R->2", 0, 0);
        addWallKickData("R->2", 1, 0);
        addWallKickData("R->2", 1, -1);
        addWallKickData("R->2", 0, 2);
        addWallKickData("R->2", 1, 2);
        addWallKickData("2->R", 0, 0);
        addWallKickData("2->R", -1, 0);
        addWallKickData("2->R", -1, 1);
        addWallKickData("2->R", 0, -2);
        addWallKickData("2->R", -1, -2);
        addWallKickData("2->L", 0, 0);
        addWallKickData("2->L", 1, 0);
        addWallKickData("2->L", 1, 1);
        addWallKickData("2->L", 0, -2);
        addWallKickData("2->L", 1, -2);
        addWallKickData("L->2", 0, 0);
        addWallKickData("L->2", -1, 0);
        addWallKickData("L->2", -1, -1);
        addWallKickData("L->2", 0, 2);
        addWallKickData("L->2", -1, 2);
        addWallKickData("L->0", 0, 0);
        addWallKickData("L->0", -1, 0);
        addWallKickData("L->0", -1, -1);
        addWallKickData("L->0", 0, 2);
        addWallKickData("L->0", -1, 2);
        addWallKickData("0->L", 0, 0);
        addWallKickData("0->L", 1, 0);
        addWallKickData("0->L", 1, 1);
        addWallKickData("0->L", 0, -2);
        addWallKickData("0->L", 1, -2);
    }

    public ArrayList<Integer[]> getZeroToR() {
        return ZeroToR;
    }

    public ArrayList<Integer[]> getRToZero() {
        return RToZero;
    }

    public ArrayList<Integer[]> getRToTwo() {
        return RToTwo;
    }

    public ArrayList<Integer[]> getTwoToR() {
        return TwoToR;
    }

    public ArrayList<Integer[]> getTwoToL() {
        return TwoToL;
    }

    public ArrayList<Integer[]> getLToTwo() {
        return LToTwo;
    }

    public ArrayList<Integer[]> getLToZero() {
        return LToZero;
    }

    public ArrayList<Integer[]> getZeroToL() {
        return ZeroToL;
    }
}
