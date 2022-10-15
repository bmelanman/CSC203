import java.awt.Color;
import java.awt.Point;

public class Circle implements Shape {
    private double radius;
    private Point center;
    private Color color;

    private double pi = Math.PI;

    public Circle(double radius, Point center, Color color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return this.center;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Circle) || (o == null)){
            return false;
        }

        Circle c = (Circle) o;

        if (this.center.equals(c.center) && this.color.equals(c.color) && this.radius == c.radius) {
            return true;
        }

        return false;
    }

    // Implemented Methods

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public double getArea() {
        return pi * radius * radius;
    }

    public double getPerimeter() {
        return 2 * pi * radius;
    }

    public void translate(Point p) {
        this.center = new Point((int) this.center.getX() + (int) p.getX(), (int) this.center.getY() + (int) p.getY());
    }

}
