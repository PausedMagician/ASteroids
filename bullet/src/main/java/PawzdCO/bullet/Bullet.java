package PawzdCO.bullet;

import PawzdCO.common.data.Entity;

public class Bullet extends Entity {
    public Bullet() {
        super();
        // Square
        double sizing = 2;
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
        this.setRadius((int) sizing);

    }
}