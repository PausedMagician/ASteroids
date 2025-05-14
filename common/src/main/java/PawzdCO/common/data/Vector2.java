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
    public Vector2(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
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

    public Vector2 add(Vector2 vector2) {
        this.x += vector2.x;
        this.y += vector2.y;
        return this;
    }
    public void addX(double x) {
        this.x += x;
    }
    public void addY(double y) {
        this.y += y;
    }

    public Vector2 subtract(Vector2 vector2) {
        this.x -= vector2.x;
        this.y -= vector2.y;
        return this;
    }

    public Vector2 subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 subtract(double x) {
        this.x -= x;
        this.y -= x;
        return this;
    }

    public void subtractX(double x) {
        this.x -= x;
    }
    public void subtractY(double y) {
        this.y -= y;
    }

    public void multiplySelf(double x) {
        this.x *= x;
        this.y *= x;
    }
    public Vector2 multiply(double x) {
        this.x *= x;
        this.y *= x;
        return this;
    }

    public Vector2 round() {
        return new Vector2(Math.round(x), Math.round(y));
    }



    public Vector2 difference(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        double length = length();
        if (length != 0) {
            x = x / length;
            y = y / length;
        }
        return this;
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
    public void lerp(Vector2 vector2, float f) {
        this.x = this.x + (vector2.x - this.x) * f;
        this.y = this.y + (vector2.y - this.y) * f;
    }

    public void max(double length) {
        if (this.length() > length) {
            // Can't use normalize() because it will change the vector
            double l = this.length();
            this.x = this.x / l * length;
            this.y = this.y / l * length;
        }
    }



    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public Vector2 divide(double value) {
        this.x /= value;
        this.y /= value;
        return this;
    }
    public int distance(Vector2 location) {
        return (int) Math.sqrt(Math.pow(location.x - this.x, 2) + Math.pow(location.y - this.y, 2));
    }
}
