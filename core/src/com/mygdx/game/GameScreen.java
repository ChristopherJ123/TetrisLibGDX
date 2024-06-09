package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.config.Config;
import com.mygdx.game.controls.KeyInputProcessor;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.gamestate.CurrentPiece;
import com.mygdx.game.gamestate.Queue;

import java.util.HashMap;
import java.util.Map;

public class GameScreen implements Screen {
    final TetrisGame tetrisGame;

    Texture tetrominoTextures;

    Map<Config.ColorEnum, TextureRegion> colorToTextureMap;
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
    KeyInputProcessor keyInputProcessor;

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
        colorToTextureMap = new HashMap<>();
        colorToTextureMap.put(Config.ColorEnum.RED, redBlock);
        colorToTextureMap.put(Config.ColorEnum.ORANGE, orangeBlock);
        colorToTextureMap.put(Config.ColorEnum.YELLOW, yellowBlock);
        colorToTextureMap.put(Config.ColorEnum.LIME, limeBlock);
        colorToTextureMap.put(Config.ColorEnum.TEAL, tealBlock);
        colorToTextureMap.put(Config.ColorEnum.BLUE, blueBlock);
        colorToTextureMap.put(Config.ColorEnum.MAGENTA, magentaBlock);

        // Set cameras
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        // Application
        board = new Board();
        queue = new Queue();
        currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);

        // Initialize key actions
        Runnable leftAction = () -> currentPiece.changeBy(-1, 0, board);
        Runnable rightAction = () -> currentPiece.changeBy(1, 0, board);
        Runnable downAction = () -> currentPiece.changeBy(0, -1, board);
        Runnable spaceAction = () -> {
            while (currentPiece.changeBy(0, -1, board));
            board.placeCurrentPiece(currentPiece);
            currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
        };

        keyInputProcessor = new KeyInputProcessor(leftAction, rightAction, downAction, spaceAction);
        Gdx.input.setInputProcessor(keyInputProcessor);


//
//        for (int i = 39; i >= 0; i--) {
//            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
//                System.out.print((board.getPlayfield()[i][j].isSolid()) ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        keyInputProcessor.update(delta); // Listen keyboard inputs

        camera.update();
        tetrisGame.batch.setProjectionMatrix(camera.combined);

        tetrisGame.batch.begin(); // Render Start
        // Render play field
        for (int i = board.getPlayfield().length-1; i >= 0; i--) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                Config.ColorEnum blockColor = board.getPlayfield()[i][j].getColorEnum();
                TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                if (blockTexture != null) {
                    tetrisGame.batch.draw(blockTexture, j * 40, i * 40, 40, 40);
                }
            }
        }

        // Render current piece
        for (int i = currentPiece.getTetromino().getShape().length-1; i >= 0; i--) {
            for (int j = 0; j < currentPiece.getTetromino().getShape()[i].length; j++) {
                Config.ColorEnum blockColor = currentPiece.getTetromino().getShape()[i][j].getColorEnum();
                TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                if (currentPiece.getTetromino().getShape()[i][j].isSolid() && blockTexture != null) {
                    tetrisGame.batch.draw(blockTexture, (j + currentPiece.getX()) * 40, (i + currentPiece.getY()) * 40, 40, 40);
                }
            }
        }
        tetrisGame.batch.end(); // Render end
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
