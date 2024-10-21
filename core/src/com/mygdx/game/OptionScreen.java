package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.spinner.SimpleFloatSpinnerModel;
import com.kotcrab.vis.ui.widget.spinner.Spinner;
import com.mygdx.game.config.GameConstants;

public class OptionScreen implements Screen {
    final TetrisGame tetrisGame;
    private final Stage stage;
    private final Preferences preferences;

    private final VisSlider ARRSlider;
    private final SimpleFloatSpinnerModel ARRModel;

    private final VisSlider DASSlider;
    private final SimpleFloatSpinnerModel DASModel;

    private final VisSlider SDFSlider;
    private final SimpleFloatSpinnerModel SDFModel;

    public OptionScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;

        if (!VisUI.isLoaded()) {
            VisUI.load();
        }

        preferences = Gdx.app.getPreferences("game-config");
        stage = new Stage(new ScreenViewport());

        // Initialize all sliders, models, and spinners
        ARRSlider = new VisSlider(0, 10, 0.1f, false);
        VisLabel ARRLabel = new VisLabel("Automatic Repeat Rate (ARR)");
        ARRModel = new SimpleFloatSpinnerModel(0, 0, 10, 0.1f, 1);
        Spinner ARRValue = new Spinner("", ARRModel);

        DASSlider = new VisSlider(0, 10, 0.1f, false);  // Initialize DAS components
        VisLabel DASLabel = new VisLabel("Delayed Auto Shift (DAS)");
        DASModel = new SimpleFloatSpinnerModel(0, 0, 10, 0.1f, 1);
        Spinner DASValue = new Spinner("", DASModel);

        SDFSlider = new VisSlider(5, 50, 1, false);  // Initialize SDF components
        VisLabel SDFLabel = new VisLabel("Soft Drop Factor (SDF)");
        SDFModel = new SimpleFloatSpinnerModel(5, 5, 50, 1);
        Spinner SDFValue = new Spinner("", SDFModel);

        VisTextButton resetButton = new VisTextButton("Reset", "blue");

        // Set initial values from preferences
        float savedARRValue = preferences.getFloat("arr_value", GameConstants.ARR);
        ARRSlider.setValue(savedARRValue);
        ARRModel.setValue(savedARRValue);

        float savedDASValue = preferences.getFloat("das_value", GameConstants.DAS);
        DASSlider.setValue(savedDASValue);
        DASModel.setValue(savedDASValue);

        float savedSDFValue = preferences.getFloat("sdf_value", GameConstants.SDF);
        SDFSlider.setValue(savedSDFValue);
        SDFModel.setValue(savedSDFValue);

        // Add listeners to sliders and spinners (after initializing everything)
        ARRSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ARRModel.setValue(ARRSlider.getValue());
                saveSettings();
            }
        });
        ARRValue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ARRSlider.setValue(ARRModel.getValue());
                saveSettings();
            }
        });

        DASSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DASModel.setValue(DASSlider.getValue());
                saveSettings();
            }
        });
        DASValue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DASSlider.setValue(DASModel.getValue());
                saveSettings();
            }
        });

        SDFSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SDFModel.setValue(SDFSlider.getValue());
                saveSettings();
            }
        });
        SDFValue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SDFSlider.setValue(SDFModel.getValue());
                saveSettings();
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ARRSlider.setValue(GameConstants.ARR);
                ARRModel.setValue(GameConstants.ARR);
                DASSlider.setValue(GameConstants.DAS);
                DASModel.setValue(GameConstants.DAS);
                SDFSlider.setValue(GameConstants.SDF);
                SDFModel.setValue(GameConstants.SDF);
            }
        });

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        table.add(ARRLabel);
        table.add(ARRSlider).width(400);
        table.add(ARRValue);
        table.row();
        table.add(DASLabel);
        table.add(DASSlider).width(400);
        table.add(DASValue);
        table.row();
        table.add(SDFLabel);
        table.add(SDFSlider).width(400);
        table.add(SDFValue);
        table.row();
        table.add(resetButton).colspan(2).width(200);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    private void saveSettings() {
        preferences.putFloat("arr_value", ARRModel.getValue());
        preferences.putFloat("das_value", DASModel.getValue());
        preferences.putFloat("sdf_value", SDFModel.getValue());
        preferences.flush();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Render the stage for UI elements
        stage.act(v);
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            tetrisGame.setScreen(new MenuScreen(tetrisGame));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // Dispose of resources
        stage.dispose();
        if (VisUI.isLoaded()) {
            VisUI.dispose();
        }
    }
}
