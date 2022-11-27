import java.util.Objects;

class Node<Point> implements Comparable<Node<Point>> {
    private final Point point;
    private Point previous;
    private int startCost;  // G Value
    private int endCost;    // H Value
    private int totalCost;

    public Node(Point point, Point previous, int startCost, int endCost) {
        this.point = point;
        this.previous = previous;
        this.startCost = startCost;
        this.endCost = endCost;
        this.totalCost = startCost + endCost;
    }

    public Node(Point point) {
        this(point, null, 0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return point.equals(((Node<?>) o).point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, previous, startCost, endCost, totalCost);
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(totalCost, n.totalCost);
    }

    @Override
    public String toString() {
        return (
                this.point.toString() + " "
                        + this.startCost + " "
                        + this.endCost + " "
                        + this.totalCost
        );
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
        this.totalCost = startCost + endCost;
    }

    public void setEndCost(int endCost) {
        this.endCost = endCost;
        this.totalCost = startCost + endCost;
    }

    public int getTotalCost() {
        return this.totalCost;
    }
}