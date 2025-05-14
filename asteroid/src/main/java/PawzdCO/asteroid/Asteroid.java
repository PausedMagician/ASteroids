package PawzdCO.asteroid;

import PawzdCO.common.data.Entity;

public class Asteroid extends Entity {
    public static double sizing = 10;
    public Asteroid() {
        super();
        // Square
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
        this.setRadius((int) sizing);
    }

    @Override
    public void kill() {
        super.kill();
        // Add score and split.
    }
}