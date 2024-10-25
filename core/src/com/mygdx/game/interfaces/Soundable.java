package com.mygdx.game.interfaces;

import com.badlogic.gdx.Gdx;

public interface Soundable {
    default void playSound(String soundName) {
        Gdx.audio.newSound(Gdx.files.internal("sound/" + soundName + ".wav")).play();
    }
    default void playSound(String soundName, String fileType) {
        Gdx.audio.newSound(Gdx.files.internal("sound/" + soundName + "." + fileType)).play();
    }
}
