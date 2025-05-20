package PawzdCO.asteroid;

import java.util.ServiceLoader;

import PawzdCO.common.data.Entity;
import PawzdCO.common.services.IAsteroidSPI;

public class Asteroid extends Entity {
    private double sizing = 10;
    
    public Asteroid(double sizing) {
        super();
        // Square
        this.sizing = sizing;
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
        this.setRadius((int) sizing);
    }

    public Asteroid() {
        this(10);
    }
    
    private void createPolygon() {
        this.setPolygon(-sizing, sizing, 0, -sizing, sizing, sizing, 0, sizing);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(radius);
        this.sizing = radius;
        this.createPolygon();
    }

    @Override
    public void kill() {
        super.kill();
        // Add score and split.
        if (this.getRadius() >= 10) {
            // System.out.println("Splitting asteroid" + this.getId());
            ServiceLoader.load(IAsteroidSPI.class).findFirst().ifPresent(asteroidSPI -> {
                asteroidSPI.spawnEntity(this);
            });
        }
    }
}