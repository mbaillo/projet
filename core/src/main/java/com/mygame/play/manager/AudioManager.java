package com.mygame.play.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager {
    private Music menuMusic;
    private Music loseMusic;

    public AudioManager() {
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song.mp3"));
        menuMusic.setLooping(true);

        loseMusic = Gdx.audio.newMusic(Gdx.files.internal("music/lose.ogg"));
    }

    public void playMenuMusic() {
        stopAll();
        menuMusic.play();
    }

    public void playLoseMusic() {
        stopAll();
        loseMusic.play();
    }

    public void stopAll() {
        if (menuMusic.isPlaying()) menuMusic.stop();
        if (loseMusic.isPlaying()) loseMusic.stop();
    }

    public void dispose() {
        menuMusic.dispose();
        loseMusic.dispose();
    }
}
