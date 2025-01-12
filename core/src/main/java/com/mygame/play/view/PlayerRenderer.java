package com.mygame.play.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygame.play.model.Player;

public class PlayerRenderer {
    private Player player;
    private Texture texture;

    public PlayerRenderer(Player player, OrthographicCamera camera) {
        this.player = player;
        this.texture = new Texture("Entite/player.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, player.getPosition().x, player.getPosition().y);
    }

    public void dispose() {
        texture.dispose();
    }
}
