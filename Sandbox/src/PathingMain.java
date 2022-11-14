import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PathingMain extends PApplet {
    private PImage wyvern;
    private PImage background;
    private PImage obstacle;
    private PImage goal;
    private List<Point> path;
    private final PathingStrategy strategy = new SingleStepPathingStrategy();
    //private PathingStrategy strategy = new AStarPathingStrategy();

    private static final int TILE_SIZE = 32;
    private GridValues[][] grid;
    private static final int ROWS = 15;
    private static final int COLS = 20;

    private enum GridValues {BACKGROUND, OBSTACLE, GOAL}

    private Point wPos;
    private Point goalPos;
    private boolean foundPath = false;

    public void settings() {
        size(640, 480);
    }

    public void setup() {
        path = new LinkedList<>();
        wPos = new Point(1, 1);
        goalPos = new Point(13, 14);

        wyvern = loadImage("images/wyvern1.bmp");
        background = loadImage("images/grass.bmp");
        obstacle = loadImage("images/vein.bmp");
        goal = loadImage("images/water.bmp");

        grid = new GridValues[ROWS][COLS];
        initialize_grid(grid);

        noLoop();
    }

    private void initialize_grid(GridValues[][] grid) {
        for (GridValues[] gridValues : grid) {
            Arrays.fill(gridValues, GridValues.BACKGROUND);
        }

        for (int row = 2; row < 8; row++) {
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

    public void draw() {
        draw_grid();
        draw_path(foundPath);

        image(wyvern, wPos.x * TILE_SIZE, wPos.y * TILE_SIZE);
    }

    private void draw_grid() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                draw_tile(row, col);
            }
        }
    }

    private void draw_path(boolean foundPath) {
        for (Point p : path) {
            if (foundPath)
                fill(128, 0, 0);
            else
                fill(0);

            rect(
                    p.x * TILE_SIZE + ((float) TILE_SIZE) * 3 / 8,
                    p.y * TILE_SIZE + ((float) TILE_SIZE) * 3 / 8,
                    ((float) TILE_SIZE) / 4,
                    ((float) TILE_SIZE) / 4
            );
        }
    }

    private void draw_tile(int row, int col) {
        switch (grid[row][col]) {
            case BACKGROUND -> image(background, col * TILE_SIZE, row * TILE_SIZE);
            case OBSTACLE -> image(obstacle, col * TILE_SIZE, row * TILE_SIZE);
            case GOAL -> image(goal, col * TILE_SIZE, row * TILE_SIZE);
        }
    }

    public static void main(String[] args) {
        PApplet.main("PathingMain");
    }

    public void keyPressed() {
        if (key == ' ') {
            path.clear();
            foundPath = findGoal(wPos, goalPos, grid, path);
            redraw();
        }
    }

    public void mousePressed() {
        Point pressed = mouseToPoint();

        if (grid[pressed.y][pressed.x] == GridValues.OBSTACLE)
            grid[pressed.y][pressed.x] = GridValues.BACKGROUND;
        else if (grid[pressed.y][pressed.x] == GridValues.BACKGROUND)
            grid[pressed.y][pressed.x] = GridValues.OBSTACLE;

        redraw();

    }

    private Point mouseToPoint() {
        return new Point(mouseX / TILE_SIZE, mouseY / TILE_SIZE);
    }

    private boolean findGoal(Point pos, Point goal, GridValues[][] grid, List<Point> path) {

        AStarPathingStrategy aStarStrat = new AStarPathingStrategy();

        List<Point> aStarPath = aStarStrat.computePath(
                pos,
                goal,
                p -> withinBounds(p, grid) && grid[p.y][p.x] != GridValues.OBSTACLE,
                Point::neighbors,
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        if (aStarPath.size() == 0) {
            System.out.println("No path found");
            return false;
        }

        System.out.println(aStarPath);

        path.addAll(aStarPath);

        return true;
    }

    private static boolean withinBounds(Point p, GridValues[][] grid) {
        return p.y >= 0 && p.y < grid.length && p.x >= 0 && p.x < grid[0].length;
    }
}

    /*
    private static final Function<Point, Stream<Point>> DIAGONAL_NEIGHBORS =
            point ->
                    Stream.<Point>builder()
                            .add(new Point(point.x - 1, point.y - 1))
                            .add(new Point(point.x + 1, point.y + 1))
                            .add(new Point(point.x - 1, point.y + 1))
                            .add(new Point(point.x + 1, point.y - 1))
                            .build();


    private static final Function<Point, Stream<Point>> DIAGONAL_CARDINAL_NEIGHBORS =
            point ->
                    Stream.<Point>builder()
                            .add(new Point(point.x - 1, point.y - 1))
                            .add(new Point(point.x + 1, point.y + 1))
                            .add(new Point(point.x - 1, point.y + 1))
                            .add(new Point(point.x + 1, point.y - 1))
                            .add(new Point(point.x, point.y - 1))
                            .add(new Point(point.x, point.y + 1))
                            .add(new Point(point.x - 1, point.y))
                            .add(new Point(point.x + 1, point.y))
                            .build();
}
*/
