package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TetrisGame extends Game {
	private WindowManager windowManager;

	public SpriteBatch batch;
	public BitmapFont font;

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
		this.setScreen(new MenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
