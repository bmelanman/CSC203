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

        for (int i = 0; i < (points.size() - 2); i++) {

            perimeter += point_length(points.get(i), points.get(i + 1));
        }

        // Close the perimeter with the first and last points
        perimeter += point_length(points.get(0), points.get(points.size() - 1));

        return perimeter;
    }

    // additional functions must be private
    private static double point_length(Point point_a, Point point_b){

        double x1 = point_a.getX();
        double y1 = point_a.getY();
        double x2 = point_b.getX();
        double y2 = point_b.getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
