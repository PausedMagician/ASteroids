package PawzdCO.bullet;

import PawzdCO.common.data.Config;
import PawzdCO.common.data.Entity;

public class Bullet extends Entity {
    public static final double BULLET_SIZING = 2;
    public Bullet() {
        super();
        // Square
        double sizing = BULLET_SIZING * Config.SIZING;
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
        this.setRadius((int) sizing);

    }
}