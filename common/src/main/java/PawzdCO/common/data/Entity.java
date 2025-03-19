package PawzdCO.common.data;

import java.util.UUID;

import javafx.scene.shape.Polygon;

public class Entity {

    UUID id = UUID.randomUUID();
    public UUID getId() {
        return id;
    }

    private boolean isAlive = true;
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    private int health;
    private int radius;
    private Vector2 location = new Vector2();
    private Vector2 prefferedLocation = new Vector2();
    private Vector2 velocity = new Vector2(0,0);
    private double rotation;

    private double mass = 1;
    
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Vector2 getLocation() {
        return location;
    }
    public void setLocation(Vector2 location) {
        this.location = location;
    }
    public void setLocation(double x, double y) {
        this.location.setX(x);
        this.location.setY(y);
    }

    public Vector2 getPrefferedLocation() {
        return prefferedLocation;
    }
    public void setPrefferedLocation(Vector2 prefferedLocation) {
        this.prefferedLocation = prefferedLocation;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    public void setVelocity(double x, double y) {
        this.velocity.setX(x);
        this.velocity.setY(y);
    }

    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }


    private Polygon polygon;
    

    @SuppressWarnings("exports")
    public Polygon getPolygon() {
        return this.polygon;
    }

    @SuppressWarnings("exports")
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public void setPolygon(double... points) {
        this.polygon = new Polygon(points);
    }

    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    public void Render() {
        this.polygon.setTranslateX(this.location.getX());
        this.polygon.setTranslateY(this.location.getY());
        this.polygon.setRotate(this.getRotation());
    }

    @Override
    public String toString() {
        // Make a string that's always the same length with full representation of the entity
        return String.format("%s:%s:%s:%s:%s:%s:%s:%s:%s:%s:%s", this.getClass().getSimpleName(), this.id.toString(), this.isAlive, this.health, this.radius, this.location, this.prefferedLocation, this.velocity, this.rotation, this.mass, this.polygon.getPoints());
    }
}
