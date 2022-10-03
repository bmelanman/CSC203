
public class Circle {

    private Point center;
    private double radius;

    public Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter(){
        return center;
    }

    public double getRadius(){
        return radius;
    }

    public double perimeter(){

        // Perimiter = 2 * pi * r
        return 2 * Math.PI * Math.abs(this.radius);
    }
    // additional functions must be private
}
