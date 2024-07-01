package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controls.Soundable;

public class MenuScreen implements Screen, Soundable {
    final TetrisGame tetrisGame;

    OrthographicCamera camera;
    Texture menu, menuB;
    Texture currentTexture;
    float switchInterval = 1.25f;
    float elapsedTime = 0;
    boolean soundPlayed = false;

    public MenuScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        menu = new Texture(Gdx.files.internal("img/GEMBLOX_A.png"));
        menuB = new Texture(Gdx.files.internal("img/GEMBLOX_B.png"));
        currentTexture = menu;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        tetrisGame.batch.setProjectionMatrix(camera.combined);

        elapsedTime += delta;
        if (elapsedTime >= switchInterval) {
            currentTexture = (currentTexture == menu) ? menuB : menu;
            elapsedTime = 0;
            soundPlayed = false;
        }

        tetrisGame.batch.begin();
        tetrisGame.batch.draw(currentTexture, 0, 0, camera.viewportWidth, camera.viewportHeight);
        if (currentTexture == menuB && !soundPlayed) {
            playSound("menuTick", "wav");
            soundPlayed = true;
        }
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
        menu.dispose();
        menuB.dispose();
    }
}
