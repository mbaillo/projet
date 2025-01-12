package com.mygame.play.model;

public class MenuModel {
    private final String playButtonImagePath;
    private final String quitButtonImagePath; // Par exemple, pour un bouton Quitter

    public MenuModel() {
        // Chemins des images des boutons
        this.playButtonImagePath = "Entite/menu/playHover.png";
        this.quitButtonImagePath = "Entite/menu/quit.png";
    }

    public String getPlayButtonImagePath() {
        return playButtonImagePath;
    }

    public String getQuitButtonImagePath() {
        return quitButtonImagePath;
    }
}
