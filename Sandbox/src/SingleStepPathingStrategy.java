import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SingleStepPathingStrategy implements PathingStrategy {
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {
        /* Does not check withinReach.  Since only a single step is taken
         * on each call, the caller will need to check if the destination
         * has been reached.
         */

        List<Point> path = new ArrayList<>();

        Point currentPos = start;

        while (!currentPos.equals(end)) {

            // Filter out invalid spots
            List<Point> ValidPositions =
                    potentialNeighbors.apply(currentPos)
                            .filter(canPassThrough)
                            .filter(p -> !path.contains(p))
                            .filter((pt) -> (!pt.equals(start) && !pt.equals(end)))
                            .collect(Collectors.toList());

            // Find the cost of each new position
            List<Integer> posCosts = ValidPositions.stream().map((p) -> p.computeCost(end)).collect(Collectors.toList());

            if (posCosts.isEmpty()){
                return path;
            }

            // Get the location of the position that gets up closest to the end
            int cheapestPosIndex = posCosts.indexOf(Collections.min(posCosts));

            currentPos = ValidPositions.get(cheapestPosIndex);

            path.add(currentPos);
        }

        return path;

    }
}