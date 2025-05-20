package PawzdCO.asteroid;

import java.util.ServiceLoader;

import PawzdCO.common.data.Config;
import PawzdCO.common.data.Entity;
import PawzdCO.common.services.IAsteroidSPI;

public class Asteroid extends Entity {
    public static final double ASTEROID_SIZING = 5;

    private double sizing = ASTEROID_SIZING;
    private int sides = 3;

    public Asteroid(double sizing) {
        super();
        // Square
        this.sizing = sizing;

        this.sides = (int) Math.round(Math.random() * 2 + 3);

        // Create a polygon with random sides
        double[] points = new double[this.sides * 2];
        for (int i = 0; i < this.sides; i++) {
            double angle = Math.toRadians(i * (360.0 / this.sides));
            points[i * 2] = Math.cos(angle) * sizing;
            points[i * 2 + 1] = Math.sin(angle) * sizing;
        }
        this.setPolygon(points);

        this.setRadius((int) sizing);
    }

    public Asteroid() {
        this(ASTEROID_SIZING * Config.SIZING);
    }
    
    private void createPolygon() {
        double[] points = new double[this.sides * 2];
        for (int i = 0; i < this.sides; i++) {
            double angle = Math.toRadians(i * (360.0 / this.sides));
            points[i * 2] = Math.cos(angle) * sizing;
            points[i * 2 + 1] = Math.sin(angle) * sizing;
        }
        this.setPolygon(points);
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
        if (this.getRadius() >= ASTEROID_SIZING * Config.SIZING) {
            // System.out.println("Splitting asteroid" + this.getId());
            ServiceLoader.load(IAsteroidSPI.class).findFirst().ifPresent(asteroidSPI -> {
                asteroidSPI.spawnEntity(this);
            });
        }
    }
}