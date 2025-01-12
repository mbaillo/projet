package com.mygame.play.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Personnage {
    public Player(float startX, float startY, MapModel mapModel) {
        super(startX, startY, "Entite/player.png", 100f, mapModel);
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) move(-1, 0, deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) move(1, 0, deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) move(0, 1, deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) move(0, -1, deltaTime);
    }
}
