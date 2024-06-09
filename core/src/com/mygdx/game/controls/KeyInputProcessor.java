package com.mygdx.game.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyInputProcessor extends InputAdapter {
    private float DASDelayTimer = 0;
    private float ARRDelayTimer = 0;
    private final float DAS = 0.4f; // Initial delay
    private final float ARR = 0.05f; // Repeat delay
    private Runnable leftAction;
    private Runnable rightAction;
    private Runnable downAction;
    private Runnable spaceAction;
    private Runnable rotateLeftAction;
    private Runnable rotateRightAction;
    private Runnable rotate180Action;
    private Runnable exitAction;

    public KeyInputProcessor() {

    }

    @Override
    public boolean keyDown(int keycode) {
        DASDelayTimer = 0;
        ARRDelayTimer = 0; // Reset the timer on key press
        switch (keycode) {
            case Input.Keys.LEFT, Input.Keys.NUMPAD_4 -> leftAction.run();
            case Input.Keys.RIGHT, Input.Keys.NUMPAD_6 -> rightAction.run();
            case Input.Keys.DOWN, Input.Keys.NUMPAD_2 -> downAction.run();
            case Input.Keys.SPACE -> spaceAction.run();
            case Input.Keys.Z, Input.Keys.NUMPAD_1 -> rotateLeftAction.run();
            case Input.Keys.X, Input.Keys.NUMPAD_3 -> rotateRightAction.run();
            case Input.Keys.A -> rotate180Action.run();
            case Input.Keys.ESCAPE -> exitAction.run();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        DASDelayTimer = 0;
        ARRDelayTimer = 0; // Reset the timer on key release
        return true;
    }

    public void update(float delta) {
        DASDelayTimer += delta;
        ARRDelayTimer += delta;
        // Repeating movements here
        if (DASDelayTimer >= DAS && ARRDelayTimer >= ARR) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
                leftAction.run();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
                rightAction.run();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
                downAction.run();
            }
            ARRDelayTimer = 0; // Reset the timer after action
        }
    }

    public void setDASDelayTimer(float DASDelayTimer) {
        this.DASDelayTimer = DASDelayTimer;
    }

    public void setARRDelayTimer(float ARRDelayTimer) {
        this.ARRDelayTimer = ARRDelayTimer;
    }

    public void setLeftAction(Runnable leftAction) {
        this.leftAction = leftAction;
    }

    public void setRightAction(Runnable rightAction) {
        this.rightAction = rightAction;
    }

    public void setDownAction(Runnable downAction) {
        this.downAction = downAction;
    }

    public void setSpaceAction(Runnable spaceAction) {
        this.spaceAction = spaceAction;
    }

    public void setRotateLeftAction(Runnable rotateLeftAction) {
        this.rotateLeftAction = rotateLeftAction;
    }

    public void setRotateRightAction(Runnable rotateRightAction) {
        this.rotateRightAction = rotateRightAction;
    }

    public void setRotate180Action(Runnable rotate180Action) {
        this.rotate180Action = rotate180Action;
    }

    public void setExitAction(Runnable exitAction) {
        this.exitAction = exitAction;
    }
}
