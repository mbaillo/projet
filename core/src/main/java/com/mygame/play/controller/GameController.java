package com.mygame.play.controller;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygame.play.model.MapModel;
import com.mygame.play.model.Monstre;
import com.mygame.play.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private MapModel mapModel;
    private Player player;
    private List<Monstre> monstres; // Liste des monstres
    private long lastUpdate;
    private boolean gameOver;

    public GameController() {
        mapModel = new MapModel("maps/carte.tmx");

        // Initialiser le joueur
        player = new Player(3 * 16, mapModel.getHeightInPixels() - 5 * 16, mapModel);

        // Initialiser les monstres
        monstres = new ArrayList<>();
        monstres.add(new Monstre(5 * 16, mapModel.getHeightInPixels() - 7 * 16, 5 * 16, 10 * 16, mapModel)); // Monstre 1
        monstres.add(new Monstre(12 * 16, mapModel.getHeightInPixels() - 10 * 16, 12 * 16, 18 * 16, mapModel)); // Monstre 2
        monstres.add(new Monstre(20 * 16, mapModel.getHeightInPixels() - 17 * 16, 20 * 16, 25 * 16, mapModel)); // Monstre 3

        monstres.add(new Monstre(30 * 16, mapModel.getHeightInPixels() - 20 * 16, 30 * 16, 35 * 16, mapModel)); // Monstre 4

        lastUpdate = TimeUtils.millis();
        gameOver = false;
    }

    public void update() {
        if (gameOver) return;

        long deltaTimeMillis = TimeUtils.timeSinceMillis(lastUpdate);
        float deltaTime = deltaTimeMillis / 1000f; // Convertir en secondes
        lastUpdate = TimeUtils.millis();

        // Mettre à jour le joueur
        player.update(deltaTime);

        // Mettre à jour chaque monstre
        for (Monstre monstre : monstres) {
            monstre.update(deltaTime);
            // Vérifier les collisions entre le joueur et chaque monstre
            if (player.collidesWith(monstre)) {
                gameOver = true;
                System.out.println("Perdue !");
                return;
            }
        }
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Monstre> getMonstres() {
        return monstres;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
