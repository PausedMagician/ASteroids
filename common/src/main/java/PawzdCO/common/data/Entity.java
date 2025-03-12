package PawzdCO.common.data;

import java.util.UUID;

import javafx.scene.shape.Polygon;

public class Entity {

    UUID id = UUID.randomUUID();
    public UUID getId() {
        return id;
    }

    private int health;
    private int radius;
    private Vector2 location = new Vector2();
    private Vector2 prefferedLocation = new Vector2();
    private double rotation;
    
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

    public void Render() {
        this.polygon.setTranslateX(this.location.getX());
        this.polygon.setTranslateY(this.location.getY());
        this.polygon.setRotate(this.getRotation());
    }
}
