import java.util.List;

public class Polygon {

    private List<Point> points;

    public Polygon(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public double perimeter() {

        List<Point> points = this.points;
        double perimeter = 0.0;

        for (int i = 0; i < (points.size() - 1); i++) {

            perimeter += point_length(points.get(i), points.get(i + 1));
        }

        // Close the perimeter with the first and last points
        perimeter += point_length(points.get(0), points.get(points.size() - 1));

        return perimeter;
    }

    // additional functions must be private
    private static double point_length(Point point_a, Point point_b){

        double x = point_a.getX() - point_b.getX();
        double y = point_a.getY() - point_b.getY();

        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return length;
    }
}
