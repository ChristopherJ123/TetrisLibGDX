package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher implements WindowManager{
	private TetrisGame tetrisGame;

	public static void main (String[] arg) {
		DesktopLauncher launcher = new DesktopLauncher();
		launcher.launchGame();
	}

	private void launchGame() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Tetris!");
		config.setWindowedMode(400, 800);
		config.useVsync(true);
		config.setForegroundFPS(60);

		tetrisGame = new TetrisGame(this);
		new Lwjgl3Application(tetrisGame, config);
	}

	@Override
	public void setWindowSize(int width, int height) {
		Lwjgl3Graphics graphics = (Lwjgl3Graphics) Gdx.graphics;
		graphics.setWindowedMode(width, height);
	}
}
