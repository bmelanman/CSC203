import java.awt.Color;
import java.awt.Point;

public class Triangle implements Shape {
    private Point a;
    private Point b;
    private Point c;
    private Color color;

    public Triangle(Point a, Point b, Point c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Point getVertexA() {
        return this.a;
    }

    public Point getVertexB() {
        return this.b;
    }

    public Point getVertexC() {
        return this.c;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Triangle) || (o == null)){
            return false;
        }

        Triangle t = (Triangle) o;

        if (this.a == t.a && this.b == t.b && this.c == t.c) {
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
        double num = (a.getX() * b.getY()) + (b.getX() * c.getY()) + (c.getX() * a.getY())
                - (a.getY() * b.getX()) - (b.getY() * c.getX()) - (c.getY() * a.getX());

        return Math.abs(num / 2);
    }

    public double getPerimeter() {
        return point_length(this.a, this.b) + point_length(this.b, this.c) + point_length(this.c, this.a);
    }

    public void translate(Point p) {
        this.a = new Point((int) this.a.getX() + (int) p.getX(), (int) this.a.getY() + (int) p.getY());
        this.b = new Point((int) this.b.getX() + (int) p.getX(), (int) this.b.getY() + (int) p.getY());
        this.c = new Point((int) this.c.getX() + (int) p.getX(), (int) this.c.getY() + (int) p.getY());
    }

    // additional functions must be private
    private static double point_length(Point point_a, Point point_b) {

        double x = point_a.getX() - point_b.getX();
        double y = point_a.getY() - point_b.getY();

        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return length;
    }

}
