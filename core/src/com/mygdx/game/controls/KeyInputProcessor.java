package com.mygdx.game.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.GameScreen;
import com.mygdx.game.interfaces.Soundable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyInputProcessor extends InputAdapter implements Soundable {
    private final GameScreen gameScreen;
    private final Preferences preferences;
    private float DASDelayTimer = 0;
    private float ARRDelayTimer = 0;
    private Runnable leftAction;
    private Runnable rightAction;
    private Runnable downAction;
    private Runnable spaceAction;
    private Runnable rotateLeftAction;
    private Runnable rotateRightAction;
    private Runnable rotate180Action;
    private Runnable holdAction;
    private Runnable exitAction;

    private final List<Integer> movementKeysPressed = new ArrayList<>();
    private final List<Integer> movementKeys = Arrays.asList(20, 21, 22, 40, 146, 148, 150);

    public KeyInputProcessor(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        preferences = Gdx.app.getPreferences("game-config");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (movementKeysPressed.isEmpty() || movementKeys.contains(keycode)) DASDelayTimer = 0;
        if (!movementKeysPressed.contains(keycode) && movementKeys.contains(keycode)) movementKeysPressed.add(keycode);
        switch (keycode) {
            case Input.Keys.LEFT, Input.Keys.NUMPAD_4 -> leftAction.run();
            case Input.Keys.RIGHT, Input.Keys.NUMPAD_6 -> rightAction.run();
            case Input.Keys.DOWN, Input.Keys.NUMPAD_2 -> downAction.run();
            case Input.Keys.SPACE -> spaceAction.run();
            case Input.Keys.Z, Input.Keys.NUMPAD_1 -> rotateLeftAction.run();
            case Input.Keys.X, Input.Keys.NUMPAD_3 -> rotateRightAction.run();
            case Input.Keys.A -> rotate180Action.run();
            case Input.Keys.C -> holdAction.run();
            case Input.Keys.ESCAPE -> exitAction.run();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (movementKeys.contains(keycode)) {
            movementKeysPressed.remove(Integer.valueOf(keycode));
            if (keycode == Input.Keys.DOWN || keycode == Input.Keys.NUMPAD_2) gameScreen.sdfMultiplier = 1;
        }
        if (movementKeysPressed.isEmpty()) DASDelayTimer = 0;
        return true;
    }

    public void update(float delta) {
        // Repeating movements here
        DASDelayTimer += delta;
        ARRDelayTimer += delta;
        if (DASDelayTimer >= preferences.getFloat("das_value") && ARRDelayTimer >= preferences.getFloat("arr_value")) {
            if (!movementKeysPressed.isEmpty()) {
                switch (movementKeysPressed.get(movementKeysPressed.size()-1)) {
                    case Input.Keys.LEFT, Input.Keys.NUMPAD_4 -> {
                        if (preferences.getFloat("arr_value") == 0) {
                            while (!gameScreen.currentPiece.hitObstacle(-1, 0)) leftAction.run();
                        } else {
                            leftAction.run();
                        }
                    }
                    case Input.Keys.RIGHT, Input.Keys.NUMPAD_6 -> {
                        if (preferences.getFloat("arr_value") == 0) {
                            while (!gameScreen.currentPiece.hitObstacle(1, 0)) rightAction.run();
                        } else {
                            rightAction.run();
                        }
                    }
                }
            }
            ARRDelayTimer = 0; // Reset the timer after action
        }
    }

    public boolean movementKeyIsPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2);
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

    public void setHoldAction(Runnable holdAction) {
        this.holdAction = holdAction;
    }

    public void setExitAction(Runnable exitAction) {
        this.exitAction = exitAction;
    }
}
