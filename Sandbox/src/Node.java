class Node<Point> implements Comparable<Node<Point>> {
    private final Point point;
    private Point previous;
    private int startCost;
    private int endCost;

    Node(Point point) {
        this(point, (int) Double.POSITIVE_INFINITY);
        this.startCost = (int) Double.POSITIVE_INFINITY;
    }

    Node(Point point, int endCost) {
        this.point = point;
        this.previous = null;
        this.startCost = 0;
        this.endCost = endCost;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(endCost, o.endCost);
    }

    public Point getPoint() {
        return this.point;
    }

    public Point getPrevious() {
        return previous;
    }

    public int getStartCost() {
        return startCost;
    }

    public void setPrevious(Point previous) {
        this.previous = previous;
    }

    public void setStartCost(int startCost) {
        this.startCost = startCost;
    }

    public void setEndCost(int endCost) {
        this.endCost = endCost;
    }
}