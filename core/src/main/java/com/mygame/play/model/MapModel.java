package com.mygame.play.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapModel {
    private TiledMap tiledMap;
    private int width, height, tileWidth, tileHeight;

    public MapModel(String mapFilePath) {
        tiledMap = new TmxMapLoader().load(mapFilePath);
        width = tiledMap.getProperties().get("width", Integer.class);
        height = tiledMap.getProperties().get("height", Integer.class);
        tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        tileHeight = tiledMap.getProperties().get("tileheight", Integer.class);
    }

    public int getWidthInPixels() {
        return width * tileWidth;
    }

    public int getHeightInPixels() {
        return height * tileHeight;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    /**
     * Vérifie si une position (en pixels) est accessible pour le joueur.
     * @param x Coordonnée X (en pixels).
     * @param y Coordonnée Y (en pixels).
     * @return true si la position est accessible, false sinon.
     */
    public boolean isAccessible(float x, float y) {
        // Convertir les coordonnées en indices de tuile
        int tileX = (int) (x / tileWidth);
        int tileY = (int) (y / tileHeight);

        // Vérifier sur les calques "arrive" ou "passage"
        TiledMapTileLayer passageLayer = (TiledMapTileLayer) tiledMap.getLayers().get("passage");
        TiledMapTileLayer arriveLayer = (TiledMapTileLayer) tiledMap.getLayers().get("arrive");
        TiledMapTileLayer murLayer = (TiledMapTileLayer) tiledMap.getLayers().get("mur");

        // Si une tuile existe dans le calque "mur", elle est bloquée
        if (murLayer != null && murLayer.getCell(tileX, tileY) != null) {
            return false; // Tuile bloquée
        }

        // Sinon, elle doit exister sur "arrive" ou "passage"
        return (passageLayer != null && passageLayer.getCell(tileX, tileY) != null) ||
            (arriveLayer != null && arriveLayer.getCell(tileX, tileY) != null);
    }

    public void dispose() {
        tiledMap.dispose();
    }
}
