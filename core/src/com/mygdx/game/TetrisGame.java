package com.mygdx.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class TetrisGame extends Game {
	private WindowManager windowManager;

	public SpriteBatch batch;
	public BitmapFont font;

	// Box2DLights Here
	World world;
	private RayHandler rayHandler;
	private OrthographicCamera camera;

	public TetrisGame(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	public void changeWindowSize(int width, int height) {
		windowManager.setWindowSize(width, height);
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font

		// Set up Box2D world (Box2DLights)
		world = new World(new Vector2(0, 0), true);

		// Set up camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Set up Ray Handler (Box2DLights)
		rayHandler = new RayHandler(world);
		rayHandler.setAmbientLight(1f);

		// Set initial screen
		this.setScreen(new MenuScreen(this));
	}

	public void render() {
		super.render(); // important!

		// Update Box2D world
		world.step(1 / 60f, 6, 2);
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		rayHandler.dispose();
		world.dispose();
	}

	public RayHandler getRayHandler() {
		return rayHandler;
	}
}
