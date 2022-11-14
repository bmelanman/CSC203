import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy implements PathingStrategy {

    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {

        Queue<Node<Point>> openList = new PriorityQueue<>();
        Map<Point, Node<Point>> closedList = new HashMap<>();
        List<Point> path = new ArrayList<>();
        List<Point> successors;
        Node<Point> nextNode;
        int currentNode_totalCost;
        int currentNode_startCost;

        Node<Point> currentNode = new Node<>(start, start.computeCost(end));
        openList.add(currentNode);

        while (!openList.isEmpty()) {

            // Pop the next node off of openList
            currentNode = openList.poll();

            // Check to see if it's one step away from the end
            if (withinReach.test(currentNode.getPoint(), end)) {

                // Load all the nodes in the route into a list
                while (currentNode.getPoint() != start) {
                    path.add(0, currentNode.getPoint());
                    currentNode = closedList.get(currentNode.getPrevious());
                }

                // Return the path
                return path;
            }

            // Generate the next set of valid points
            successors = potentialNeighbors.apply(currentNode.getPoint())
                    .filter(canPassThrough)
                    .collect(Collectors.toList());

            // This will be used for cost calculation
            currentNode_startCost = currentNode.getStartCost();

            // Go through each point and calculate their costs
            for (Point nextPoint : successors) {

                // Check if the node has been explored,
                // or make a new node and add it to the closed List
                nextNode = closedList.getOrDefault(nextPoint, new Node<>(nextPoint));

                closedList.put(nextPoint, nextNode);

                // Calculate the cost of this point.
                // Add 1 to the cost because all successors
                // are 1 step away from the parent node.
                currentNode_totalCost = currentNode_startCost + 1;

                // If this point is cheaper than our current point,
                // add it to the list of possible next positions
                if (currentNode_totalCost < nextNode.getStartCost()) {
                    nextNode.setPrevious(currentNode.getPoint());
                    nextNode.setStartCost(currentNode_totalCost);
                    nextNode.setEndCost(currentNode_totalCost + nextPoint.computeCost(end));
                    openList.add(nextNode);
                }
            }

            // Add the current node to the map of explored nodes
            closedList.put(currentNode.getPoint(), currentNode);
        }

        return new ArrayList<>();
    }
}