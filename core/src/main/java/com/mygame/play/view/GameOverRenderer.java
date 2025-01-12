package com.mygame.play.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.play.manager.GameStateManager;

public class GameOverRenderer {
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final GameStateManager gameStateManager;

    private final Texture quitButtonTexture;
    private final Texture continueButtonTexture;
    private final float quitButtonX, quitButtonY;
    private final float continueButtonX, continueButtonY;

    public GameOverRenderer(SpriteBatch spriteBatch, OrthographicCamera camera, GameStateManager gameStateManager) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.gameStateManager = gameStateManager;

        // Charger les textures
        quitButtonTexture = new Texture("Entite/menu/quit.png");
        continueButtonTexture = new Texture("Entite/menu/continue.png");

        // Positionner les boutons au centre
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        quitButtonX = (screenWidth - quitButtonTexture.getWidth()) / 2f - 150;
        quitButtonY = (screenHeight - quitButtonTexture.getHeight()) / 2f;

        continueButtonX = (screenWidth - continueButtonTexture.getWidth()) / 2f + 150;
        continueButtonY = quitButtonY;
    }

    public void update(float deltaTime) {
        if (Gdx.input.isTouched()) {
            int touchX = Gdx.input.getX();
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Vérifier si le bouton Quit est cliqué
            if (touchX >= quitButtonX && touchX <= quitButtonX + quitButtonTexture.getWidth() &&
                touchY >= quitButtonY && touchY <= quitButtonY + quitButtonTexture.getHeight()) {
                Gdx.app.exit();
            }

            // Vérifier si le bouton Continue est cliqué
            if (touchX >= continueButtonX && touchX <= continueButtonX + continueButtonTexture.getWidth() &&
                touchY >= continueButtonY && touchY <= continueButtonY + continueButtonTexture.getHeight()) {
                gameStateManager.changeState(GameStateManager.GameState.GAME);
            }
        }
    }

    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        // Dessiner les boutons
        spriteBatch.draw(quitButtonTexture, quitButtonX, quitButtonY);
        spriteBatch.draw(continueButtonTexture, continueButtonX, continueButtonY);

        spriteBatch.end();
    }

    public void dispose() {
        quitButtonTexture.dispose();
        continueButtonTexture.dispose();
    }
}
