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

    public void multiplySelf(double x) {
        this.x *= x;
        this.y *= y;
    }
    public Vector2 multiply(double x) {
        this.x *= x;
        this.y *= y;
        return this;
    }



    public Vector2 difference(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double length = length();
        if (length != 0) {
            x = x / length;
            y = y / length;
        }
    }

    public double dot(Vector2 v) {
        return x * v.x + y * v.y;
    }

    public double angle(Vector2 v) {
        return Math.acos(dot(v) / (length() * v.length()));
    }

    public double cross(Vector2 v) {
        return x * v.y - y * v.x;
    }
}
