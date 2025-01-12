package com.mygame.play.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.play.manager.GameStateManager;
import com.mygame.play.model.MenuModel;

public class MenuRenderer {
    private final Texture playButtonTexture;
    private final Texture quitButtonTexture; // Nouveau bouton
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final GameStateManager gameStateManager;

    private float playButtonX, playButtonY;
    private float quitButtonX, quitButtonY;

    public MenuRenderer(SpriteBatch spriteBatch, OrthographicCamera camera, GameStateManager gameStateManager) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.gameStateManager = gameStateManager;

        MenuModel menuModel = new MenuModel();
        this.playButtonTexture = new Texture(menuModel.getPlayButtonImagePath());
        this.quitButtonTexture = new Texture(menuModel.getQuitButtonImagePath());

        // Positionner les boutons
        this.playButtonX = (Gdx.graphics.getWidth() - playButtonTexture.getWidth()) / 2f;
        this.playButtonY = (Gdx.graphics.getHeight() + playButtonTexture.getHeight()) / 2f;

        this.quitButtonX = (Gdx.graphics.getWidth() - quitButtonTexture.getWidth()) / 2f;
        this.quitButtonY = playButtonY - 150; // Espacement en dessous du bouton Play
    }

    public void update(float deltaTime) {
        if (Gdx.input.isTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Inverser Y

            // Gestion du clic sur le bouton Play
            if (touchX >= playButtonX && touchX <= playButtonX + playButtonTexture.getWidth() &&
                    touchY >= playButtonY && touchY <= playButtonY + playButtonTexture.getHeight()) {
                gameStateManager.changeState(GameStateManager.GameState.GAME);
            }

            // Gestion du clic sur le bouton Quit
            if (touchX >= quitButtonX && touchX <= quitButtonX + quitButtonTexture.getWidth() &&
                    touchY >= quitButtonY && touchY <= quitButtonY + quitButtonTexture.getHeight()) {
                Gdx.app.exit();
            }
        }
    }

    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(playButtonTexture, playButtonX, playButtonY);
        spriteBatch.draw(quitButtonTexture, quitButtonX, quitButtonY);
        spriteBatch.end();
    }

    public void dispose() {
        playButtonTexture.dispose();
        quitButtonTexture.dispose();
    }
}
