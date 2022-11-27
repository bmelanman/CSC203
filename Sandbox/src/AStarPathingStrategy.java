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

        if (start == null || end == null){
            return new ArrayList<>();
        }

        List<Point> path = new ArrayList<>();
        Node<Point> currentNode;
        List<Point> successors;
        Node<Point> nextNode;
        Node<Point> nodeCheck;

        // openList: A lit of potential next steps in the path
        Queue<Node<Point>> openList = new PriorityQueue<>();
        Map<Point, Node<Point>> openListMap = new HashMap<>();
        // closedList: A map of all points that have been visited
        Map<Point, Node<Point>> closedList = new HashMap<>();

        // Convert the start point to a Node and add it to the openList
        openList.add(new Node<>(start, null, 0, start.computeCost(end)));
        openListMap.put(start, openList.peek());

        // Iterate through different points until we reach
        // the end or there are no more available points
        while (!openList.isEmpty()) {

//            System.out.println(openList.size());
//            System.out.println(openListMap.size());
//            System.out.println("-----");

            // Pop the next node off of openList with the lowest f-value
            currentNode = openList.poll();
            openListMap.remove(currentNode.getPoint());

            // Generate the next set of valid points
            successors = potentialNeighbors.apply(currentNode.getPoint())
                    .filter(canPassThrough)
                    .collect(Collectors.toList());

            // Go through each point and calculate their costs
            for (Point nextPoint : successors) {

                // Visual aid
                if (PathingMain.getOccupancy(nextPoint) != PathingMain.GridValues.SEARCHED){
                    PathingMain.setOccupancy(nextPoint);
                }

                // Check to see if nextPoint is one step away
                // from the end and return the path if it is
                if (withinReach.test(nextPoint, end)) {

                    // Make sure there are no infinite loops in the path
                    if (detectLoop(currentNode, closedList)) {
                        System.out.println("Loop!");
                        return new ArrayList<>();
                    }

                    // Add the last point to the path
                    path.add(0, nextPoint);

                    // Load all the nodes in the route into a list
                    while (currentNode.getPoint() != start) {
                        path.add(0, currentNode.getPoint());
                        currentNode = closedList.get(currentNode.getPrevious());
                    }

                    // Return the path
                    return path;
                }

                // Generate a new node from nextPoint
                nextNode = new Node<>(nextPoint);
                // g-value = (currentPoint g-value) + (distance between currentPoint and nextPoint)
                nextNode.setStartCost(currentNode.getStartCost() + currentNode.getPoint().computeCost(nextPoint));
                // h-value = (distance between nextPoint and the end point)
                nextNode.setEndCost(nextPoint.computeCost(end));

                // Check if the point has already been traversed with a smaller f-value
                nodeCheck = openListMap.get(nextPoint);

                // If there is a duplicate, one of the two points will be removed
                if(nodeCheck != null){
                    if (nodeCheck.getTotalCost() < nextNode.getTotalCost()){
                        // Existing node is better, ignore the newer node
                        continue;
                    }
                    // New node is better, remove the old node
                    openList.remove(nodeCheck);
                    openListMap.remove(nodeCheck.getPoint());
                }

                // Check if the node has been explored
                nodeCheck = closedList.get(nextPoint);

                // If the node is not found in the HashMap, or the found point has a
                // larger f-value than nextPoint, save the node to be explored later
                if (nodeCheck == null || nodeCheck.getTotalCost() > nextNode.getTotalCost()) {

                    // If there are no duplicates, add the next point
                    // to openList and the implied LinkedList of nodes
                    nextNode.setPrevious(currentNode.getPoint());
                    openList.add(nextNode);
                    openListMap.put(nextPoint, nextNode);
                }

            }

            // Add the current node to the map of explored nodes
            closedList.put(currentNode.getPoint(), currentNode);
        }

        // If we exited the while loop, that
        // means there is no path available
        return new ArrayList<>();
    }

    private boolean detectLoop(Node<Point> node, Map<Point, Node<Point>> map) {

        HashSet<Point> traversedNodes = new HashSet<>();

        while (node != null) {

            if (traversedNodes.contains(node.getPoint()))
                return true;

            traversedNodes.add(node.getPoint());

            node = map.get(node.getPrevious());
        }

        return false;
    }
}