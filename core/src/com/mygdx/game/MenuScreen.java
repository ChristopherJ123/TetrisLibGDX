package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    final TetrisGame tetrisGame;

    OrthographicCamera camera;

    public MenuScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        tetrisGame.batch.setProjectionMatrix(camera.combined);

        tetrisGame.batch.begin();
        tetrisGame.font.draw(tetrisGame.batch, "Welcome to Tetris!!! ", 50, 150);
        tetrisGame.font.draw(tetrisGame.batch, "Tap anywhere to begin!", 50, 100);
        tetrisGame.batch.end();

        if (Gdx.input.isTouched()) {
            tetrisGame.setScreen(new GameScreen(tetrisGame));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
