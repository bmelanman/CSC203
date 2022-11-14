import java.awt.geom.Point2D;

final class Point {
    public final int x;
    public final int y;
    public String id;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point &&
                ((Point) other).x == this.x &&
                ((Point) other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }

    public int computeCost(Point p) {
        return (int) Point2D.distance(x, y, p.x, p.y);
    }

    public boolean neighbors(Point p) {
        return x + 1 == p.x && y == p.y || x - 1 == p.x && y == p.y ||
                x == p.x && y + 1 == p.y || x == p.x && y - 1 == p.y;
    }
}
