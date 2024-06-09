package com.mygdx.game.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyInputProcessor extends InputAdapter {
    private float DASDelayTimer = 0;
    private float ARRDelayTimer = 0;
    private final float DAS = 0.4f; // Initial delay
    private final float ARR = 0.05f; // Repeat delay
    private final Runnable leftAction;
    private final Runnable rightAction;
    private final Runnable downAction;
    private final Runnable spaceAction;

    public KeyInputProcessor(Runnable leftAction, Runnable rightAction, Runnable downAction, Runnable spaceAction) {
        this.leftAction = leftAction;
        this.rightAction = rightAction;
        this.downAction = downAction;
        this.spaceAction = spaceAction;
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
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                spaceAction.run();
            }
            ARRDelayTimer = 0; // Reset the timer after action
        }
    }
}
