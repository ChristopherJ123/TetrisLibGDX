package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.config.Config;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.gamestate.CurrentPiece;
import com.mygdx.game.gamestate.Queue;

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

    Queue queue;
    Board board;
    CurrentPiece currentPiece;

    public GameScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;

        // Batch of sprites
        tetrisGame.batch = new SpriteBatch();

        // Load textures
        tetrominoTextures = new Texture(Gdx.files.internal("img/texture.png"));

        redBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        orangeBlock = new TextureRegion(tetrominoTextures, 192, 0, 192, 192);
        yellowBlock = new TextureRegion(tetrominoTextures, 384, 0, 192, 192);
        limeBlock = new TextureRegion(tetrominoTextures, 576, 0, 192, 192);
        tealBlock = new TextureRegion(tetrominoTextures, 768, 0, 192, 192);
        blueBlock = new TextureRegion(tetrominoTextures, 0, 192, 192, 192);
        magentaBlock = new TextureRegion(tetrominoTextures, 192, 192, 192, 192);
        grayBlock = new TextureRegion(tetrominoTextures, 384, 192, 192, 192);

        // Set cameras
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        // Application
        board = new Board();
        queue = new Queue();
        System.out.println(queue.getQueue());
        currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
        board.addCurrentPiece(currentPiece);

        for (int i = 39; i >= 0; i--) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                System.out.print((board.getPlayfield()[i][j].isSolid()) ? "1 " : "0 ");
            }
            System.out.println();
        }
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
        for (int i = board.getPlayfield().length-1; i >= 0; i--) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                switch (board.getPlayfield()[i][j].getColor()) {
                    case RED -> tetrisGame.batch.draw(redBlock, j * 40, i * 40, 40, 40);
                    case ORANGE -> tetrisGame.batch.draw(orangeBlock, j * 40, i * 40, 40, 40);
                    case YELLOW -> tetrisGame.batch.draw(yellowBlock, j * 40, i * 40, 40, 40);
                    case LIME -> tetrisGame.batch.draw(limeBlock, j * 40, i * 40, 40, 40);
                    case TEAL -> tetrisGame.batch.draw(tealBlock, j * 40, i * 40, 40, 40);
                    case BLUE -> tetrisGame.batch.draw(blueBlock, j * 40, i * 40, 40, 40);
                    case MAGENTA -> tetrisGame.batch.draw(magentaBlock, j * 40, i * 40, 40, 40);
                }
            }
        }
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
