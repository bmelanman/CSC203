import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathingMain extends PApplet {
    private List<PImage> images;
    private int current_image;
    private long next_time;
    private PImage background;
    private PImage obstacle;
    private PImage goal;
    private List<Point> path;
    private static final int TILE_SIZE = 32;
    private static final int ANIMATION_TIME = 100;
    private GridValues[][] grid;
    private static final int ROWS = 15;
    private static final int COLS = 20;

    public enum GridValues {BACKGROUND, OBSTACLE, GOAL, SEARCHED, DEAD_END}

    private Point wPos;
    private static Point goalPos;
    private boolean drawPath = false;

    Function<Point, Stream<Point>> CARDINAL_NEIGHBORS =
            point ->
                    Stream.<Point>builder()
                            .add(new Point(point.x + 1, point.y))
                            .add(new Point(point.x, point.y + 1))
                            .add(new Point(point.x - 1, point.y))
                            .add(new Point(point.x, point.y - 1))
                            .build();

    public void settings() {
        size(640, 480);
    }

    /* runs once to set up world */
    public void setup() {
        path = new LinkedList<>();
        wPos = new Point(2, 2);
        goalPos = new Point(14, 13);
        images = new ArrayList<>();
        images.add(loadImage("images/wyvern1.bmp"));
        images.add(loadImage("images/wyvern2.bmp"));
        images.add(loadImage("images/wyvern3.bmp"));

        background = loadImage("images/grass.bmp");
        obstacle = loadImage("images/vein.bmp");
        goal = loadImage("images/water.bmp");

        grid = new GridValues[ROWS][COLS];
        initialize_grid(grid);

        current_image = 0;
        next_time = System.currentTimeMillis() + ANIMATION_TIME;
    }

    /* set up a 2D grid to represent the world */
    private static void initialize_grid(GridValues[][] grid) {
        for (GridValues[] gridValues : grid) {
            Arrays.fill(gridValues, GridValues.BACKGROUND);
        }

        //set up some obstacles
        for (int row = 3; row < 8; row++) {
            grid[row][row + 5] = GridValues.OBSTACLE;
        }

        for (int row = 8; row < 12; row++) {
            grid[row][19 - row] = GridValues.OBSTACLE;
        }

        for (int col = 1; col < 8; col++) {
            grid[11][col] = GridValues.OBSTACLE;
        }

        grid[goalPos.y][goalPos.x] = GridValues.GOAL;
    }

    private void next_image() {
        current_image = (current_image + 1) % images.size();
    }

    /* runs over and over */
    public void draw() {
        // A simplified action scheduling handler
        long time = System.currentTimeMillis();
        if (time >= next_time) {
            next_image();
            next_time = time + ANIMATION_TIME;
        }

        draw_grid();
        draw_path();

        image(images.get(current_image), wPos.x * TILE_SIZE, wPos.y * TILE_SIZE);
    }

    private void draw_grid() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                draw_tile(row, col);
            }
        }
    }

    private void draw_path() {
        if (drawPath) {
            for (Point p : path) {
                fill(128, 0, 0);
                rect(
                        (p.x * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
                        (p.y * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
                        (float) (TILE_SIZE / 4),
                        (float) (TILE_SIZE / 4)
                );
            }
        }
    }

    private void draw_tile(int row, int col) {
        switch (grid[row][col]) {
            case BACKGROUND -> image(background, col * TILE_SIZE, row * TILE_SIZE);
            case OBSTACLE -> image(obstacle, col * TILE_SIZE, row * TILE_SIZE);
            case SEARCHED, DEAD_END -> {
                fill(0, 128);
                rect(
                        col * TILE_SIZE + (float) (TILE_SIZE / 4),
                        row * TILE_SIZE + (float) (TILE_SIZE / 4),
                        (float) (TILE_SIZE / 2),
                        (float) (TILE_SIZE / 2)
                );
            }
            case GOAL -> image(goal, col * TILE_SIZE, row * TILE_SIZE);
        }
    }

    public void mousePressed() {
        Point pressed = mouseToPoint();
        List<GridValues> spaces = List.of(new GridValues[]{GridValues.BACKGROUND, GridValues.SEARCHED, GridValues.DEAD_END});

        if (grid[pressed.y][pressed.x] == GridValues.OBSTACLE)
            grid[pressed.y][pressed.x] = GridValues.BACKGROUND;
        else if (spaces.contains(grid[pressed.y][pressed.x])) {
            grid[pressed.y][pressed.x] = GridValues.OBSTACLE;
        }

        redraw();
    }

    private Point mouseToPoint() {
        return new Point(mouseX / TILE_SIZE, mouseY / TILE_SIZE);
    }

    public static void main(String[] args) {
        PApplet.main("PathingMain");
    }

    public void keyPressed() {
        if (key == ' ') {
            //clear out prior path and re-initialize grid
            path.clear();
            initialize_grid(grid);

            depthFirstSearch(wPos, goalPos);

        } else if (key == 'p') {
            drawPath ^= true;

        } else if (key == 'c') {
            path.clear();
            initialize_grid(grid);

        }
    }

    private void depthFirstSearch(Point start, Point end) {

        Predicate<Point> canTraverse =
                (p) -> (withinBounds(p)
                        && getOccupancy(p) != GridValues.OBSTACLE
                        && getOccupancy(p) != GridValues.DEAD_END);

        Predicate<Point> checkSearched =
                (p) -> (getOccupancy(p) != GridValues.SEARCHED);

        List<Point> successors;
        List<Point> nextPoints;
        Point currentPoint = start;

        while (!currentPoint.neighbors(end)) {

            fill(128, 0, 0);
            rect(
                    (currentPoint.x * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
                    (currentPoint.y * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
                    (float) (TILE_SIZE / 4),
                    (float) (TILE_SIZE / 4)
            );
            // Set the current point as searched
            setOccupancy(currentPoint, GridValues.SEARCHED);

            // Generate a set of valid next points
            successors = CARDINAL_NEIGHBORS.apply(currentPoint)
                    .filter(canTraverse)
                    .collect(Collectors.toList());

            // If there are no traversable points, return an empty list (isn't that clever?)
            if (successors.isEmpty()) {
                System.out.println("No path!");
                path.clear();
                return;
            }

            // Filter out points that have been traversed
            nextPoints = successors.stream().filter(checkSearched).collect(Collectors.toList());

            // If there are no valid and untraveled points, we'll use
            // a traversed point and remove the current point mark it
            // as a dead end which will never be visited again
            if (nextPoints.isEmpty()) {
                setOccupancy(currentPoint, GridValues.DEAD_END);
                path.remove(path.size() - 1);
                nextPoints = successors;
            }

            // Grab what ever point is first in line
            currentPoint = nextPoints.get(0);

            // Add it to the path and hope for the best
            path.add(currentPoint);
        }

        // Make sure we mark the last point as visited
        setOccupancy(currentPoint, GridValues.SEARCHED);
    }

    private PathingMain.GridValues getOccupancy(Point p) {
        return grid[p.y][p.x];
    }

    private void setOccupancy(Point p, PathingMain.GridValues value) {
        grid[p.y][p.x] = value;
    }

    private boolean withinBounds(Point p) {
        return p.y >= 0 && p.y < grid.length &&
                p.x >= 0 && p.x < grid[0].length;
    }
}
