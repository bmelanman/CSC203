import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DFSPathingStrategy_copy implements PathingStrategy {
    @Override
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        Predicate<Point> checkSearched = p -> getOccupancy(p, PathingMain.grid) != PathingMain.GridValues.SEARCHED;

        List<Point> path = new ArrayList<>();
        List<Point> successors;
        List<Point> nextPoints;

        Point currentPoint = start;

        while (!withinReach.test(currentPoint, end)) {

            // Set the current point as searched
            setOccupancy(currentPoint, PathingMain.grid, PathingMain.GridValues.SEARCHED);

            // Generate a set of valid next points
            successors = potentialNeighbors.apply(currentPoint)
                    .filter(canPassThrough)
                    .collect(Collectors.toList());

            // If there are no traversable points, return an empty list (isn't that clever?)
            if (successors.isEmpty()) {
                System.out.println("No path!");
                path.clear();
                path.add(currentPoint);
                return path;
            }

            // Filter out points that have been traversed
            nextPoints = successors.stream().filter(checkSearched).collect(Collectors.toList());

            // If there are no valid and untraveled points, we'll use
            // a traversed point and remove the current point mark it
            // as a dead end which will never be visited again
            if (nextPoints.isEmpty()) {
                setOccupancy(currentPoint, PathingMain.grid, PathingMain.GridValues.DEAD_END);
                removeLast(path);
                nextPoints = successors;
            }

            // Grab what ever point is first in line
            currentPoint = nextPoints.get(0);

            // Add the point only if it is not currently in the list of points
            if (!path.contains(currentPoint)) {
                path.add(currentPoint);
            }

        }

        // Make sure we mark the last point as visited
        setOccupancy(currentPoint, PathingMain.grid, PathingMain.GridValues.SEARCHED);
        return path;
    }

    static PathingMain.GridValues getOccupancy(Point p, PathingMain.GridValues[][] grid) {
        return grid[p.y()][p.x()];
    }

    private static void setOccupancy(Point p, PathingMain.GridValues[][] grid, PathingMain.GridValues value) {
        grid[p.y()][p.x()] = value;
    }

    private void removeLast(List<Point> path) {

        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
    }
}
