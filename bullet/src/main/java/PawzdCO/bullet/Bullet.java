package PawzdCO.bullet;

import PawzdCO.common.data.Entity;

public class Bullet extends Entity {
    public static double sizing = 2;
    public Bullet() {
        super();
        // Square
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
        this.setRadius((int) sizing);

    }
}