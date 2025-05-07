package PawzdCO.player;

import PawzdCO.common.data.Entity;

public class Player extends Entity {
    public Player() {
        super();
        this.setPolygon(-5, 5, 0, -7.5, 5, 5, 0, 2.5);
        this.setRadius(5);
    }
}