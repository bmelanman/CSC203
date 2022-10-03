import java.util.List;

public class Util {

    public static double perimeter(Circle circle){

        // Perimiter = 2 * pi * r
        return 2 * Math.PI * circle.getRadius();
    }

    public static double perimeter(Rectangle rectangle){

        Point top_left = rectangle.getTopLeft();
        Point bottom_right = rectangle.getBottomRight();

        double width = Math.abs(top_left.getX() - bottom_right.getX());
        double length = Math.abs(bottom_right.getY() - top_left.getY());

        return (2 * width) + (2 * length);
    }

    public static double perimeter(Polygon polygon){

        List<Point> points = polygon.getPoints();

        double perimeter = 0.0;

        for (int i = 0; i < (points.size() - 1); i++){
            perimeter += point_length(points.get(i), points.get(i + 1));
        }

        // Close the perimeter with the first and last points
        return perimeter += point_length(points.get(0), points.get(points.size() - 1));
    }

    private static double point_length(Point point_a, Point point_b){

        double x = point_a.getX() - point_b.getX();
        double y = point_a.getY() - point_b.getY();

        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return length;
    }
}
