package com.mygame.play.model;

public class Monstre extends Personnage {
    private float minX, maxX; // Limites de déplacement horizontal
    private boolean movingRight; // Direction actuelle

    public Monstre(float startX, float startY, float minX, float maxX, MapModel mapModel) {
        super(startX, startY, "Entite/monstre.png", 50f, mapModel); // Monstre plus lent que le joueur
        this.minX = minX;
        this.maxX = maxX;
        this.movingRight = true; // Commence en allant à droite
    }

    @Override
    public void update(float deltaTime) {
        // Déplacement horizontal
        if (movingRight) {
            move(1, 0, deltaTime); // Aller à droite
            if (position.x >= maxX) {
                movingRight = false; // Changer de direction
            }
        } else {
            move(-1, 0, deltaTime); // Aller à gauche
            if (position.x <= minX) {
                movingRight = true; // Changer de direction
            }
        }
    }
    @Override
    public void move(float dx, float dy, float deltaTime) {
        float newX = position.x + dx * speed * deltaTime;
        float newY = position.y + dy * speed * deltaTime;

        System.out.println("Monstre position actuelle : " + position.x + ", " + position.y);
        System.out.println("Tentative déplacement vers : " + newX + ", " + newY);

        // Vérifier si la nouvelle position est accessible
        if (mapModel.isAccessible(newX, position.y)) {
            position.x = newX; // Déplacer horizontalement
            System.out.println("Déplacement réussi vers : " + newX + ", " + position.y);
        } else {
            // Si bloqué, inverser la direction
            movingRight = !movingRight;
            System.out.println("Bloqué, changement de direction.");
        }
    }


}
