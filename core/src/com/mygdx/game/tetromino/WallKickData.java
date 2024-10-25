package com.mygdx.game.tetromino;

import java.util.ArrayList;

public class WallKickData {
    private final ArrayList<Object[]> ZeroToR;
    private final ArrayList<Object[]> RToZero;
    private final ArrayList<Object[]> RToTwo;
    private final ArrayList<Object[]> TwoToR;
    private final ArrayList<Object[]> TwoToL;
    private final ArrayList<Object[]> LToTwo;
    private final ArrayList<Object[]> LToZero;
    private final ArrayList<Object[]> ZeroToL;

    public WallKickData() {
        ZeroToR = new ArrayList<>();
        RToZero = new ArrayList<>();
        RToTwo = new ArrayList<>();
        TwoToR = new ArrayList<>();
        TwoToL = new ArrayList<>();
        LToTwo = new ArrayList<>();
        LToZero = new ArrayList<>();
        ZeroToL = new ArrayList<>();
    }

    public boolean addWallKickData(String type, int x, int y) {
        switch (type) {
            case "0->R" -> ZeroToR.add(new Object[]{x, y,});
            case "R->0" -> RToZero.add(new Object[]{x, y});
            case "R->2" -> RToTwo.add(new Object[]{x, y});
            case "2->R" -> TwoToR.add(new Object[]{x, y});
            case "2->L" -> TwoToL.add(new Object[]{x, y});
            case "L->2" -> LToTwo.add(new Object[]{x, y});
            case "L->0" -> LToZero.add(new Object[]{x, y});
            case "0->L" -> ZeroToL.add(new Object[]{x, y});
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

    public ArrayList<Object[]> getZeroToR() {
        return ZeroToR;
    }

    public ArrayList<Object[]> getRToZero() {
        return RToZero;
    }

    public ArrayList<Object[]> getRToTwo() {
        return RToTwo;
    }

    public ArrayList<Object[]> getTwoToR() {
        return TwoToR;
    }

    public ArrayList<Object[]> getTwoToL() {
        return TwoToL;
    }

    public ArrayList<Object[]> getLToTwo() {
        return LToTwo;
    }

    public ArrayList<Object[]> getLToZero() {
        return LToZero;
    }

    public ArrayList<Object[]> getZeroToL() {
        return ZeroToL;
    }
}
