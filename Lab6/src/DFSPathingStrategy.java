import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DFSPathingStrategy implements PathingStrategy {

    // Globals make things easier
    private final List<Point> path = new ArrayList<>();
    private Predicate<Point> canPassThrough;
    private BiPredicate<Point, Point> withinReach;
    private Function<Point, Stream<Point>> potentialNeighbors;
    private final Predicate<Point> checkSearched = p -> PathingMain.getOccupancy(p) != PathingMain.GridValues.SEARCHED;

    @Override
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        this.canPassThrough = canPassThrough;
        this.withinReach = withinReach;
        this.potentialNeighbors = potentialNeighbors;

        path.clear();
        if (DepthFirstSearch(start, end)) {
            return path;
        }

        return new ArrayList<>();
    }

    private boolean DepthFirstSearch(Point point, Point end) {
        boolean found;

        // Check if inputs are null
        if (point == null || end == null) {
            return false;
        }

        // Check if we've found the end
        if (withinReach.test(point, end)) {
            path.add(0, point);
            return true;
        }

        // Generate a list of possible next points
        List<Point> nextPoints = potentialNeighbors.apply(point)
                .filter(canPassThrough)
                .filter(checkSearched)
                .toList();

        // Iterate through the points and try to find one that reaches the end
        for (Point nextPoint : nextPoints) {
            // Visual aid
            PathingMain.setOccupancy(nextPoint);

            // Recuse through the next node
            found = DepthFirstSearch(nextPoint, end);

            // If we found the end, this is a valid path
            if (found) {

                // Add this point to the path and return true
                path.add(0, point);
                return true;
            }
        }

        // If no end is available, return false
        return false;
    }
}
