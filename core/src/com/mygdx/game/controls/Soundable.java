package com.mygdx.game.controls;

import com.badlogic.gdx.Gdx;

public interface Soundable {
    default void playSound(String soundName, String fileType) {
        Gdx.audio.newSound(Gdx.files.internal("sound/" + soundName + "." + fileType)).play();
    }
}
