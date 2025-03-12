package PawzdCO.asteroid;

import java.util.Random;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;

public class Asteroid extends Entity {
    private Vector2 velocity = new Vector2(0,0);

    public Asteroid() {
        Random r = new Random();
        this.setRadius(r.nextInt(10) + 10);
        
        double radians = Math.toRadians(r.nextInt(360));
        // Create a polygon with 3-6 points
        int points = r.nextInt(4) + 3;
        double[] poly = new double[points * 2];
        for (int i = 0; i < points; i++) {
            double x = Math.cos(radians + Math.toRadians(360 / points * i)) * (this.getRadius()-2);
            double y = Math.sin(radians + Math.toRadians(360 / points * i)) * (this.getRadius()-2);
            poly[i * 2] = x;
            poly[i * 2 + 1] = y;
        }
        this.setPolygon(poly);
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    public void addVelocity(Vector2 velocity) {
        this.velocity.add(velocity);
    }
    
}