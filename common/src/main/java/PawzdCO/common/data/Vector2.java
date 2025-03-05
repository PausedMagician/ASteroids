package PawzdCO.common.data;

public class Vector2 {
    double x, y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    public void add(Vector2 vector2) {
        this.x += vector2.x;
        this.y += vector2.y;
    }
    public void addX(double x) {
        this.x += x;
    }
    public void addY(double y) {
        this.y += y;
    }
}
