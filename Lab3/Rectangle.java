import java.awt.Color;
import java.awt.Point;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private Point topLeft;
    private Color color;

    // A constructor with parameters to initialize all its instance variables.
    public Rectangle(double width, double height, Point topLeft, Color color) {
        this.width = width;
        this.height = height;
        this.topLeft = topLeft;
        this.color = color;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Point getTopLeft() {
        return this.topLeft;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Rectangle) || (o == null)){
            return false;
        }

        Rectangle r = (Rectangle) o;

        if (this.width == r.width && this.height == r.height && this.topLeft.equals(r.topLeft) && this.color.equals(r.color)) {
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
        return width * height;
    }

    public double getPerimeter() {
        return width + height + width + height;
    }

    public void translate(Point p) {
        this.topLeft = new Point((int) this.topLeft.getX() + (int) p.getX(),
                (int) this.topLeft.getY() + (int) p.getY());
    }

}
