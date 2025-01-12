package com.mygame.play.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygame.play.model.MapModel;

public class MapRenderer {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapModel mapModel;

    public MapRenderer(MapModel mapModel, OrthographicCamera camera) {
        this.mapModel = mapModel;
        this.camera = camera;
        this.renderer = new OrthogonalTiledMapRenderer(mapModel.getTiledMap());

        // Ajuster la position initiale de la caméra pour centrer la carte
        camera.position.set(mapModel.getWidthInPixels() / 2f, mapModel.getHeightInPixels() / 2f, 0);
        camera.update();
    }

    public void render() {
        // Mettre à jour la vue de la carte en fonction de la caméra
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        renderer.dispose();
    }
}
