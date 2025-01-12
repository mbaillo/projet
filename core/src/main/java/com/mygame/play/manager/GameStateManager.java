package com.mygame.play.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.play.view.GameScreen;
import com.mygame.play.view.GameOverRenderer;
import com.mygame.play.view.MenuRenderer;

public class GameStateManager {
    public enum GameState {
        MENU, GAME, GAME_OVER
    }

    private GameState currentState;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final AudioManager audioManager;

    private MenuRenderer menuRenderer;
    private GameScreen gameScreen;
    private GameOverRenderer gameOverRenderer;

    public GameStateManager(SpriteBatch spriteBatch, OrthographicCamera camera, AudioManager audioManager) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.audioManager = audioManager;

        initializeStates();
        currentState = GameState.MENU;
        audioManager.playMenuMusic();
    }

    private void initializeStates() {
        menuRenderer = new MenuRenderer(spriteBatch, camera, this);
        gameScreen = new GameScreen(
            new com.mygame.play.controller.GameController(),
            new com.mygame.play.view.MapRenderer(new com.mygame.play.controller.GameController().getMapModel(), camera),
            spriteBatch,
            camera,
            this
        );
        gameOverRenderer = new GameOverRenderer(spriteBatch, camera, this);
    }

    public void update(float deltaTime) {
        switch (currentState) {
            case MENU:
                menuRenderer.update(deltaTime);
                break;
            case GAME:
                gameScreen.render(deltaTime); // Appelle le render avec delta pour gérer la logique du jeu
                break;
            case GAME_OVER:
                gameOverRenderer.update(deltaTime);
                break;
        }
    }

    public void render() {
        switch (currentState) {
            case MENU:
                menuRenderer.render();
                break;
            case GAME:
                // Le rendu est déjà intégré dans le `gameScreen.render`
                break;
            case GAME_OVER:
                gameOverRenderer.render();
                break;
        }
    }

    public void changeState(GameState newState) {
        if (currentState == newState) return;

        // Arrête les musiques associées à l'état précédent
        audioManager.stopAll();

        currentState = newState;
        switch (currentState) {
            case MENU:
                audioManager.playMenuMusic();
                break;
            case GAME:
                // Réinitialiser l'écran de jeu si nécessaire
                initializeGame();
                break;
            case GAME_OVER:
                audioManager.playLoseMusic();
                break;
        }
    }

    private void initializeGame() {
        gameScreen = new GameScreen(
            new com.mygame.play.controller.GameController(),
            new com.mygame.play.view.MapRenderer(new com.mygame.play.controller.GameController().getMapModel(), camera),
            spriteBatch,
            camera,
            this
        );
    }

    public void dispose() {
        menuRenderer.dispose();
        gameScreen.dispose();
        gameOverRenderer.dispose();
        audioManager.dispose();
    }
}
