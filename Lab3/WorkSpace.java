import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class WorkSpace {

    private List<Shape> workspace;

    public WorkSpace() {
        this.workspace = new ArrayList<>();
    }

    public void add(Shape shape) {
        this.workspace.add(shape);
    }

    public Shape get(int index) {
        return this.workspace.get(index);
    }

    public int size() {
        return this.workspace.size();
    }

    public List<Circle> getCircles() {

        List<Circle> circles = new ArrayList<>();

        for (Shape shape : this.workspace) {

            if (shape instanceof Circle) {
                circles.add((Circle) shape);
            }
        }

        return circles;
    }

    public List<Rectangle> getRectangles() {

        List<Rectangle> rectangles = new ArrayList<>();

        for (Shape shape : this.workspace) {

            if (shape instanceof Rectangle) {
                rectangles.add((Rectangle) shape);
            }
        }

        return rectangles;
    }

    public List<Triangle> getTriangles() {

        List<Triangle> triangles = new ArrayList<>();

        for (Shape shape : this.workspace) {

            if (shape instanceof Triangle) {
                triangles.add((Triangle) shape);
            }
        }

        return triangles;
    }

    public List<Shape> getShapesByColor(Color color) {

        List<Shape> shapes = new ArrayList<>();

        for (Shape shape : this.workspace) {
            if (shape.getColor() == color) {
                shapes.add(shape);
            }
        }

        return shapes;
    }

    public double getAreaOfAllShapes() {

        double area = 0.0;

        for (Shape shape : this.workspace) {
            area += shape.getArea();
        }

        return area;
    }

    public double getPerimeterOfAllShapes() {

        double perimeter = 0.0;

        for (Shape shape : this.workspace) {
            perimeter += shape.getPerimeter();
        }

        return perimeter;
    }

}
