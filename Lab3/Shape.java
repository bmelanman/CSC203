import java.awt.Color;
import java.awt.Point;

public interface Shape {

    // Returns the java.awt.Color of the Shape
    public Color getColor();

    // Sets the java.awt.Color of the Shape
    public void setColor(Color c);

    //  Returns the area of the Shape
    public double getArea();

    // Returns the perimeter of the shape
    public double getPerimeter();

    // Translates the entire shape by the (x,y) coordinates of a given java.awt.Point
    public void translate(Point p);

}
