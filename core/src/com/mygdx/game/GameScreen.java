package com.mygdx.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.config.Config;
import com.mygdx.game.controls.KeyInputProcessor;
import com.mygdx.game.gamestate.Board;
import com.mygdx.game.gamestate.CurrentPiece;
import com.mygdx.game.gamestate.Queue;
import com.mygdx.game.gamestate.Score;

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

    Board board;
    Queue queue;
    CurrentPiece currentPiece;
    Score score;
    KeyInputProcessor keyInputProcessor;

    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;

    // Box2DLights Here
    World world;
    RayHandler rayHandler;

    public GameScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        tetrisGame.changeWindowSize(600, 800);

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

        // Set up Box2D world
        world = new World(new Vector2(0, 0), true);

        // Set cameras
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        shapeRenderer = new ShapeRenderer();

        rayHandler = new RayHandler(world);

        // Application
        board = new Board();
        queue = new Queue();
        currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
        score = new Score();

        // Initialize key actions
        Runnable leftAction = () -> currentPiece.changeBy(-1, 0, board);
        Runnable rightAction = () -> currentPiece.changeBy(1, 0, board);
        Runnable downAction = () -> currentPiece.changeBy(0, -1, board);
        Runnable spaceAction = () -> {
            while (currentPiece.changeBy(0, -1, board));
            board.placeCurrentPiece(currentPiece);
            score.clearLineIfAny(board);
            currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
        };
        Runnable rotateLeftAction = () -> currentPiece.rotateBy(-1, board);
        Runnable rotateRightAction = () -> currentPiece.rotateBy(1, board);
        Runnable rotate180Action = () -> currentPiece.rotateBy(2, board);
        Runnable exitAction = () -> System.exit(0);

        keyInputProcessor = new KeyInputProcessor();
        keyInputProcessor.setLeftAction(leftAction);
        keyInputProcessor.setRightAction(rightAction);
        keyInputProcessor.setDownAction(downAction);
        keyInputProcessor.setSpaceAction(spaceAction);
        keyInputProcessor.setRotateLeftAction(rotateLeftAction);
        keyInputProcessor.setRotateRightAction(rotateRightAction);
        keyInputProcessor.setRotate180Action(rotate180Action);
        keyInputProcessor.setExitAction(exitAction);
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
        ScreenUtils.clear(0, 0, 0, 1);

        keyInputProcessor.update(delta); // Listen keyboard inputs

        camera.update();
        tetrisGame.batch.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // Render start
        shapeRenderer.setColor(0, 0, 0.2f, 1); // Set the color to red
        shapeRenderer.rect(400, 0, 200, 800); // Example position and size
        shapeRenderer.end(); // Render end

        tetrisGame.batch.begin(); // Render start
        // Render play field
        for (int i = 0; i < board.getPlayfield().length; i++) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                Config.ColorEnum blockColor = board.getPlayfield()[i][j].getColorEnum();
                TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                if (blockTexture != null) {
                    tetrisGame.batch.draw(blockTexture, j * 40, i * 40, 40, 40);
                }
            }
        }

        // Render current piece
        for (int i = 0; i < currentPiece.getTetromino().getShape().length; i++) {
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
        System.out.println("Screen is resized to " + width + " " + height);
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
