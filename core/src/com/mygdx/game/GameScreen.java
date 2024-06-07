package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final TetrisGame tetrisGame;

    Texture tetrominoTextures;

    TextureRegion redBlock;
    TextureRegion orangeBlock;
    TextureRegion yellowBlock;
    TextureRegion limeBlock;
    TextureRegion tealBlock;
    TextureRegion blueBlock;
    TextureRegion magentaBlock;
    TextureRegion grayBlock;

    OrthographicCamera camera;

    public GameScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;

        // Batch of sprites
        tetrisGame.batch = new SpriteBatch();

        // Load textures
        tetrominoTextures = new Texture(Gdx.files.internal("img/texture.png"));

        redBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        orangeBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        yellowBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        limeBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        tealBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        blueBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        magentaBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        grayBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);

        // Set cameras
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        // Rendering
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
        tetrisGame.batch.draw(redBlock, 100, 100, 40, 40);
        tetrisGame.batch.end();
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
        tetrominoTextures.dispose();
    }
}
