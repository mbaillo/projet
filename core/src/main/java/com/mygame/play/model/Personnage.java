package com.mygame.play.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Personnage {
    protected Vector2 position;
    protected float speed; // Vitesse de déplacement
    protected Texture texture;
    protected MapModel mapModel;

    public Personnage(float startX, float startY, String texturePath, float speed, MapModel mapModel) {
        this.position = new Vector2(startX, startY);
        this.texture = new Texture(texturePath);
        this.speed = speed;
        this.mapModel = mapModel;
    }

    public abstract void update(float deltaTime); // Mise à jour spécifique à chaque personnage

    public void move(float dx, float dy, float deltaTime) {
        float newX = position.x + dx * speed * deltaTime;
        float newY = position.y + dy * speed * deltaTime;

        // Vérifier si la nouvelle position est accessible
        if (mapModel.isAccessible(newX, newY)) {
            position.set(newX, newY);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean collidesWith(Personnage other) {
        return position.dst(other.getPosition()) < 16; // Distance inférieure à la taille d'une tuile
    }
}
