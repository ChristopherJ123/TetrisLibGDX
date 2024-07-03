package com.mygdx.game;

import box2dLight.PointLight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.config.Config;
import com.mygdx.game.controls.KeyInputProcessor;
import com.mygdx.game.controls.Soundable;
import com.mygdx.game.gamestate.*;
import com.mygdx.game.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScreen implements Screen, Soundable {
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
    TextureRegion placementBlock;
    TextureRegion whiteBlock;
    Music backgroundMusic;
    Board board;
    Queue queue;
    CurrentPiece currentPiece;
    HoldPiece holdPiece;
    Score score;
    KeyInputProcessor keyInputProcessor;
    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;
    BitmapFont font;
    GlyphLayout layout;
    Texture holdInside;
    Texture holdSign;
    Texture queueInside;
    Texture queueSign;
    Texture scoreSign;
    Texture blackTexture;
    Texture gameOverText;
    Texture retryButton;

    private float AutoDropDelayTimer = 0;
    private boolean isGameover = false;

    // Box2DLights stuff
    List<PointLight> lights;

    public GameScreen(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        tetrisGame.changeWindowSize(1200, 800);

        // Batch of sprites
        tetrisGame.batch = new SpriteBatch();

        // Load textures
        tetrominoTextures = new Texture(Gdx.files.internal("img/texture.png"));
        holdInside = new Texture(Gdx.files.internal("img/holdInside.png"));
        queueInside = new Texture(Gdx.files.internal("img/queueInside.png"));
        gameOverText = new Texture(Gdx.files.internal("img/gameOverText.png"));
        retryButton = new Texture(Gdx.files.internal("img/retryButton.png"));
        redBlock = new TextureRegion(tetrominoTextures, 0, 0, 192, 192);
        orangeBlock = new TextureRegion(tetrominoTextures, 192, 0, 192, 192);
        yellowBlock = new TextureRegion(tetrominoTextures, 384, 0, 192, 192);
        limeBlock = new TextureRegion(tetrominoTextures, 576, 0, 192, 192);
        tealBlock = new TextureRegion(tetrominoTextures, 768, 0, 192, 192);
        blueBlock = new TextureRegion(tetrominoTextures, 0, 192, 192, 192);
        magentaBlock = new TextureRegion(tetrominoTextures, 192, 192, 192, 192);
        placementBlock = new TextureRegion(tetrominoTextures, 768, 192, 192, 192);
        whiteBlock = new TextureRegion (tetrominoTextures, 576, 192, 192, 192);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/TetrisAlt.wav"));
        colorToTextureMap = new HashMap<>();
        colorToTextureMap.put(Config.ColorEnum.RED, redBlock);
        colorToTextureMap.put(Config.ColorEnum.ORANGE, orangeBlock);
        colorToTextureMap.put(Config.ColorEnum.YELLOW, yellowBlock);
        colorToTextureMap.put(Config.ColorEnum.LIME, limeBlock);
        colorToTextureMap.put(Config.ColorEnum.TEAL, tealBlock);
        colorToTextureMap.put(Config.ColorEnum.BLUE, blueBlock);
        colorToTextureMap.put(Config.ColorEnum.MAGENTA, magentaBlock);
        colorToTextureMap.put(Config.ColorEnum.PLACEMENT, placementBlock);

        // Set cameras
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        shapeRenderer = new ShapeRenderer();

        // Application
        board = new Board();
        queue = new Queue();
        currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
        holdPiece = new HoldPiece();
        score = new Score();
        blackTexture = new Texture(1, 1, Pixmap.Format.RGBA8888);
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.75f); // Black with 50% opacity
        pixmap.fill();
        blackTexture.draw(pixmap, 0, 0);
        pixmap.dispose();

        // Music
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        // Text
        font = new BitmapFont();
        layout = new GlyphLayout();
        holdSign = new Texture(Gdx.files.internal("img/holdSign.png"));
        queueSign = new Texture(Gdx.files.internal("img/nextSign.png"));
        scoreSign = new Texture(Gdx.files.internal("img/scoreSign.png"));

        // Box2DLights Stuff
        lights = new ArrayList<>();

        // Initialize key actions
        Runnable leftAction = () -> currentPiece.moveLeft(board);
        Runnable rightAction = () -> currentPiece.moveRight(board);
        Runnable downAction = () -> currentPiece.moveDown(board);
        Runnable spaceAction = () -> {
            while (currentPiece.changeBy(0, -1, board));
            board.placeCurrentPiece(currentPiece);
            score.clearLineIfAny(board);
            currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
            if (currentPiece.hitObstacle(board)) {
                isGameover = true;
                playSound("gameOverSound", "wav");
            }
            holdPiece.setRecentlyUsedHold(false);
        };
        Runnable rotateLeftAction = () -> currentPiece.rotateLeft(board);
        Runnable rotateRightAction = () -> currentPiece.rotateRight(board);
        Runnable rotate180Action = () -> currentPiece.rotate180(board);
        Runnable holdAction = () -> {
            if (!holdPiece.isRecentlyUsedHold()) {
                Tetromino holdingPiece = holdPiece.set(currentPiece.getTetromino());
                if (holdingPiece != null) currentPiece = new CurrentPiece(holdingPiece, Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
                else currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
                holdPiece.setRecentlyUsedHold(true);
                playSound("hold", "wav");
            }
        };
        Runnable exitAction = () -> System.exit(0);

        keyInputProcessor = new KeyInputProcessor();
        keyInputProcessor.setLeftAction(leftAction);
        keyInputProcessor.setRightAction(rightAction);
        keyInputProcessor.setDownAction(downAction);
        keyInputProcessor.setSpaceAction(spaceAction);
        keyInputProcessor.setRotateLeftAction(rotateLeftAction);
        keyInputProcessor.setRotateRightAction(rotateRightAction);
        keyInputProcessor.setRotate180Action(rotate180Action);
        keyInputProcessor.setHoldAction(holdAction);
        keyInputProcessor.setExitAction(exitAction);
        Gdx.input.setInputProcessor(keyInputProcessor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        keyInputProcessor.update(delta); // Listen keyboard inputs

        AutoDropDelayTimer += delta; // Timer for automatic drop
        // Automatic Drop delay
        if (AutoDropDelayTimer >= Config.AUTO_DROP_DELAY - (float) score.getScore() /(5000+score.getScore())) {
            if (!currentPiece.moveDown(board)) {
                board.placeCurrentPiece(currentPiece);
                score.clearLineIfAny(board);
                currentPiece = new CurrentPiece(queue.nextQueue(), Config.BOARD_WIDTH / 2, Config.BOARD_HEIGHT - 3);
                if (currentPiece.hitObstacle(board)) {
                    isGameover = true;
                    playSound("gameOverSound", "wav");
                }

                holdPiece.setRecentlyUsedHold(false);
            }
            AutoDropDelayTimer = 0;
        }

        camera.update();
        tetrisGame.batch.setProjectionMatrix(camera.combined);

        shapeRenderer.setProjectionMatrix(camera.combined);

        renderBackground();
        renderGrid();

        tetrisGame.batch.begin(); // Render batch start
        renderPlayField(); // Render play field
        renderCurrentPiece(); // Render current piece
        renderPlacementOutlinePiece(); // Render placement outline piece
        tetrisGame.batch.draw(holdInside, 185, 520);
        tetrisGame.batch.draw(queueInside, 815, 80);
        renderQueue(); // Render Queue
        renderHoldPiece(); // Render Hold Piece
        tetrisGame.batch.draw(scoreSign, 250, 240);
        font.getData().setScale(2);
        layout.setText(font, "" + score.getScore());
        layout.setText(font, "" + score.getScore());
        float scoreWidth = layout.width;
        font.draw(tetrisGame.batch, layout, 368 - scoreWidth, 230);
        tetrisGame.batch.draw(queueSign, 800, 720);
        tetrisGame.batch.draw(holdSign, 170, 720);
        tetrisGame.batch.end(); // Render batch end

        renderBox2DLights(); // Render Box2DLights

        boolean gameOverPlayed = false;

        if (isGameover) {
            pause();
            tetrisGame.batch.begin();
            tetrisGame.batch.draw(blackTexture, 0, 0, 1200, 800);
            tetrisGame.batch.draw(gameOverText, 440, 400, 320, 320);
            tetrisGame.batch.draw(retryButton, 560, 200, 80, 80);
            AutoDropDelayTimer = 0;
            tetrisGame.batch.end();

            if (Gdx.input.isTouched()) {
                playSound("gameStart", "wav");
                dispose();
            }
        }
    }

    public void renderPlayField() {
        for (int i = 0; i < board.getPlayfield().length; i++) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                Config.ColorEnum blockColor = board.getPlayfield()[i][j].getColorEnum();
                TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                if (blockTexture != null) {
                    tetrisGame.batch.draw(blockTexture, 400 + (j * 40), i * 40, 40, 40);
                }
            }
        }
    }

    public void renderGrid () {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(1, 1, 1, 0.05f));

        // x 400 y 800
        for (int x = 0; x <= Config.BOARD_WIDTH * 40; x += 40) {
            shapeRenderer.line(x + 400, 0, x + 400, Config.BOARD_HEIGHT * 40);
        }

        for (int y = 0; y <= Config.BOARD_HEIGHT * 40; y += 40) {
            shapeRenderer.line(400, y, (Config.BOARD_WIDTH * 40) + 400, y);
        }

        shapeRenderer.end();
    }

    public void renderCurrentPiece() {
        for (int i = 0; i < currentPiece.getTetromino().getShape().length; i++) {
            for (int j = 0; j < currentPiece.getTetromino().getShape()[i].length; j++) {
                Config.ColorEnum blockColor = currentPiece.getTetromino().getShape()[i][j].getColorEnum();
                TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                if (currentPiece.getTetromino().getShape()[i][j].isSolid() && blockTexture != null) {
                    tetrisGame.batch.draw(blockTexture, 400+ ((j + currentPiece.getX()) * 40), (i + currentPiece.getY()) * 40, 40, 40);
                }
            }
        }
    }

    public void renderPlacementOutlinePiece() {
        tetrisGame.batch.setColor(1, 1, 1, 0.5f);
        for (int i = 0; i < currentPiece.getTetromino().getShape().length; i++) {
            for (int j = 0; j < currentPiece.getTetromino().getShape()[i].length; j++) {
                if (currentPiece.getTetromino().getShape()[i][j].isSolid()) {
                    tetrisGame.batch.draw(colorToTextureMap.get(Config.ColorEnum.PLACEMENT), 400 + ((j + currentPiece.getX()) * 40), (i + currentPiece.getPlacementY(board) + 1) * 40, 40, 40);
                }
            }
        }
        tetrisGame.batch.setColor(1, 1, 1, 1);
    }
    public void renderQueue() {
        ArrayList<Config.Tetrominoes> currentQueue = queue.getQueue();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < currentQueue.get(i).getType().getShape().length; j++) {
                for (int k = 0; k < currentQueue.get(i).getType().getShape()[j].length; k++) {
                    Config.ColorEnum blockColor = currentQueue.get(i).getType().getShape()[j][k].getColorEnum();
                    TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                    if (currentQueue.get(i).getType().getShape()[j][k].isSolid() && blockTexture != null) {
                        tetrisGame.batch.draw(blockTexture, 415 + 440 + (k * 40), 120*(5-i) + (j * 40) - 80 + 35, 40, 40);
                    }
                }
            }
        }
    }
    public void renderHoldPiece() {
        if (holdPiece.getTetromino() != null) {
            for (int i = 0; i < holdPiece.getTetromino().getShape().length; i++) {
                for (int j = 0; j < holdPiece.getTetromino().getShape()[i].length; j++) {
                    Config.ColorEnum blockColor = holdPiece.getTetromino().getShape()[i][j].getColorEnum();
                    TextureRegion blockTexture = colorToTextureMap.get(blockColor);
                    if (holdPiece.getTetromino().getShape()[i][j].isSolid() && blockTexture != null) {
                        tetrisGame.batch.draw(blockTexture, 225 + (j * 40), 535 + (i * 40), 40, 40);
                    }
                }
            }
        }
    }

    public void renderBox2DLights() {
        for (PointLight light : lights) {
            light.remove();
        }
        lights.clear();
        for (int i = 0; i < currentPiece.getTetromino().getShape().length; i++) {
            for (int j = 0; j < currentPiece.getTetromino().getShape()[i].length; j++) {
                if (currentPiece.getTetromino().getShape()[i][j].isSolid()) {
                    Config.ColorEnum blockColor = currentPiece.getTetromino().getShape()[i][j].getColorEnum();
                    Color color = blockColor.getColor();
                    float x = (j + currentPiece.getX()) * 40 + 20;
                    float y = (i + currentPiece.getY()) * 40 + 20;
                    PointLight pointLight = new PointLight(tetrisGame.getRayHandler(), 100, color, 55, 400 + x, y);
                    lights.add(pointLight);
                }
            }
        }
        for (int i = 0; i < board.getPlayfield().length; i++) {
            for (int j = 0; j < board.getPlayfield()[i].length; j++) {
                Config.ColorEnum blockColor = board.getPlayfield()[i][j].getColorEnum();
                Color color = blockColor.getColor();
                PointLight pointLight = new PointLight(tetrisGame.getRayHandler(), 100, color, 55, 400 + (j*40) + 20, (i*40) + 20);
                lights.add(pointLight);
            }
        }
        if (holdPiece.getTetromino() != null) {
            for (int i = 0; i < holdPiece.getTetromino().getShape().length; i++) {
                for (int j = 0; j < holdPiece.getTetromino().getShape()[i].length; j++) {
                    if (holdPiece.getTetromino().getShape()[i][j].isSolid()) {
                        Config.ColorEnum blockColor = holdPiece.getTetromino().getShape()[i][j].getColorEnum();
                        Color color = blockColor.getColor();
                        float x = 225 + (j * 40) + 20;
                        float y = 535 + (i * 40) + 20;
                        PointLight pointLight = new PointLight(tetrisGame.getRayHandler(), 100, color, 55, x, y);
                        lights.add(pointLight);
                    }
                }
            }
        }
        ArrayList<Config.Tetrominoes> currentQueue = queue.getQueue();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < currentQueue.get(i).getType().getShape().length; j++) {
                for (int k = 0; k < currentQueue.get(i).getType().getShape()[j].length; k++) {
                    if (currentQueue.get(i).getType().getShape()[j][k].isSolid()) {
                        Config.ColorEnum blockColor = currentQueue.get(i).getType().getShape()[j][k].getColorEnum();
                        Color color = blockColor.getColor();
                        float x = 415 + 440 + (k * 40) + 20;
                        float y = 120 * (5 - i) + (j * 40) - 80 + 20 + 35;
                        PointLight pointLight = new PointLight(tetrisGame.getRayHandler(), 100, color, 55, x, y);
                        lights.add(pointLight);
                    }
                }
            }
        }
        tetrisGame.getRayHandler().setCombinedMatrix(camera);
        tetrisGame.getRayHandler().updateAndRender();
        // Render Box2DLights end
    }

    public void renderBackground() {
        // Enable blending for gradient rendering
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Set up the gradient colors
        Color topColor = new Color(0, 0, 0, 1); // #000000
        Color bottomColor = new Color(12/255f, 66/255f, 107/255f, 1); // #2C2C2C
        Color bottomColorB = new Color(0, 8/255f, 39/255f, 1);

        // Set up the ShapeRenderer for gradient rendering
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, 400, 800, bottomColor, bottomColor, topColor, topColor);
        shapeRenderer.rect(800, 0, 400, 800, bottomColor, bottomColor, topColor, topColor);
        shapeRenderer.rect(400, 0, 400, 800, bottomColorB, bottomColorB, topColor, topColor);
        shapeRenderer.end();

        // Disable blending
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // Draw borders after the gradient
        renderBorders();
    }

    public void renderBorders() {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);

        // Hold Border
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(238/255f, 225/255f, 0, 1);
        shapeRenderer.rect(170, 505, 230, 15); // Top border
        shapeRenderer.rect(170, 505, 15, 230); // Left border
        shapeRenderer.rect(170, 720, 230, 15); // Bottom border
        shapeRenderer.rect(385, 505, 15, 230); // Right border
        shapeRenderer.end();

        // Playfield Border
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(238/255f, 225/255f, 0, 1);
        shapeRenderer.rect(385, -15, (Config.BOARD_WIDTH * 40) + 30, 15); // Top border
        shapeRenderer.rect(385, -15, 15, (Config.BOARD_HEIGHT * 40) + 30); // Left border
        shapeRenderer.rect(385, Config.BOARD_HEIGHT * 40 + 15, (Config.BOARD_WIDTH * 40) + 30, 15); // Bottom border
        shapeRenderer.rect(385 + (Config.BOARD_WIDTH * 40) + 15, -15, 15, (Config.BOARD_HEIGHT * 40) + 30); // Right border
        shapeRenderer.end();

        // Queue Border
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(238/255f, 225/255f, 0, 1);
        shapeRenderer.rect(800, 65, 230, 15); // Top border
        shapeRenderer.rect(800, 65, 15, 670); // Left border
        shapeRenderer.rect(800, 695 + 25, 230, 15); // Bottom border
        shapeRenderer.rect(800 + 230 - 15, 65, 15, 670); // Right border
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
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
        shapeRenderer.dispose();
        tetrisGame.getRayHandler().dispose();
        for (PointLight light : lights) {
            light.remove();
        }
        lights.clear();
        tetrisGame.getRayHandler().dispose();
        blackTexture.dispose();
        holdInside.dispose();
        queueInside.dispose();
        gameOverText.dispose();
        retryButton.dispose();
    }
}
