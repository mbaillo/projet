package com.mygame.play.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.play.controller.GameController;

public class GameRenderer {
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final GameController gameController;

    public GameRenderer(SpriteBatch spriteBatch, OrthographicCamera camera, GameController gameController) {
        this.spriteBatch = spriteBatch;
        this.camera = camera;
        this.gameController = gameController;
    }

    public void update() {
        gameController.update(); // Appel sans deltaTime
    }

    public void render() {
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        gameController.getPlayer().draw(spriteBatch);
        gameController.getMonstres().forEach(monstre -> monstre.draw(spriteBatch));
        spriteBatch.end();
    }

    public void dispose() {
        // Libérez les ressources si nécessaire
    }
}
