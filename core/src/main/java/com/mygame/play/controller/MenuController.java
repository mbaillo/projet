package com.mygame.play.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MenuController {
    private final Rectangle playButtonBounds;

    public MenuController(float x, float y, float width, float height) {
        this.playButtonBounds = new Rectangle(x, y, width, height);
    }

    public boolean isPlayButtonClicked() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            return playButtonBounds.contains(clickPosition);
        }
        return false;
    }
}
