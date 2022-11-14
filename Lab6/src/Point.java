public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean neighbors(Point p) {
        return x + 1 == p.x && y == p.y || x - 1 == p.x && y == p.y ||
                x == p.x && y + 1 == p.y || x == p.x && y - 1 == p.y;
    }
}
