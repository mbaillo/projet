package com.mygame.play;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.play.manager.AudioManager;
import com.mygame.play.manager.GameStateManager;

public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private GameStateManager gameStateManager;
    private AudioManager audioManager;

    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        spriteBatch = new SpriteBatch();

        audioManager = new AudioManager();
        gameStateManager = new GameStateManager(spriteBatch, camera, audioManager);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        gameStateManager.dispose();
        audioManager.dispose();
    }
}
