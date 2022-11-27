public record Point(int x, int y) {

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean neighbors(Point p) {
        return x + 1 == p.x() && y == p.y() || x - 1 == p.x() && y == p.y() ||
                x == p.x() && y + 1 == p.y() || x == p.x() && y - 1 == p.y();
    }
}
