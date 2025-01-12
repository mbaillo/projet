package com.mygame.play.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygame.play.controller.GameController;
import com.mygame.play.manager.GameStateManager;
import com.mygame.play.model.Monstre;

public class GameScreen implements Screen {
    private final GameController gameController;
    private final MapRenderer mapRenderer;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final GameStateManager gameStateManager;

    public GameScreen(GameController gameController, MapRenderer mapRenderer, SpriteBatch spriteBatch, OrthographicCamera camera, GameStateManager gameStateManager) {
        this.gameController = gameController;
        this.mapRenderer = mapRenderer;
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1);

        camera.update();
        mapRenderer.render();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        // Dessiner le joueur
        gameController.getPlayer().draw(spriteBatch);

        // Dessiner chaque monstre
        for (Monstre monstre : gameController.getMonstres()) {
            monstre.draw(spriteBatch);
        }

        spriteBatch.end();

        // Mettre à jour le jeu
        gameController.update();

        // Vérifier la condition de défaite
        if (gameController.isGameOver()) {
            System.out.println("Partie terminée. Perdue !");
            gameStateManager.changeState(GameStateManager.GameState.GAME_OVER); // Transition vers GAME_OVER
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        mapRenderer.dispose();
        gameController.getMapModel().dispose();
        gameController.getPlayer().dispose();
        for (Monstre monstre : gameController.getMonstres()) {
            monstre.dispose();
        }
    }
}
