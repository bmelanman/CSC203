import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

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
    private static final int ROWS = 15;
    private static final int COLS = 20;
    private Point wPos;
    private static Point goalPos;
    private boolean drawPath = false;
    public static GridValues[][] grid;
    private final PathingStrategy strategy = new DFSPathingStrategy();
    private final Predicate<Point> canTraverse = p -> (
            withinBounds(p)
                    && !p.equals(wPos)
                    && getOccupancy(p) != PathingMain.GridValues.OBSTACLE
                    && getOccupancy(p) != PathingMain.GridValues.DEAD_END
    );

    enum GridValues {BACKGROUND, OBSTACLE, GOAL, SEARCHED, DEAD_END}

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
        initialize_grid();

        current_image = 0;
        next_time = System.currentTimeMillis() + ANIMATION_TIME;
    }

    /* set up a 2D grid to represent the world */
    private void initialize_grid() {
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

        grid[goalPos.y()][goalPos.x()] = GridValues.GOAL;
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

        image(images.get(current_image), wPos.x() * TILE_SIZE, wPos.y() * TILE_SIZE);
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
                        (p.x() * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
                        (p.y() * TILE_SIZE) + (float) ((TILE_SIZE * 3) / 8),
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

    private void clear_searched() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == GridValues.SEARCHED || grid[i][j] == GridValues.DEAD_END) {
                    grid[i][j] = GridValues.BACKGROUND;
                }
            }
        }
    }

    public void mousePressed() {
        Point pressed = mouseToPoint();
        List<GridValues> spaces = List.of(new GridValues[]{GridValues.BACKGROUND, GridValues.SEARCHED, GridValues.DEAD_END});

        if (grid[pressed.y()][pressed.x()] == GridValues.OBSTACLE)
            grid[pressed.y()][pressed.x()] = GridValues.BACKGROUND;
        else if (spaces.contains(grid[pressed.y()][pressed.x()])) {
            grid[pressed.y()][pressed.x()] = GridValues.OBSTACLE;
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
            //clear out prior path and remove old search flags
            path.clear();
            clear_searched();

            // Run pathing algorithm
            path = strategy.computePath(
                    wPos, goalPos,
                    canTraverse,
                    Point::neighbors,
                    PathingStrategy.CARDINAL_NEIGHBORS_RDLU
            );

        } else if (key == 'p') {
            drawPath ^= true;

        } else if (key == 'c') {
            path.clear();
            initialize_grid();

        }
    }

    boolean withinBounds(Point p) {
        return p.y() >= 0 && p.y() < grid.length &&
                p.x() >= 0 && p.x() < grid[0].length;
    }

    static void setOccupancy(Point p) {
        grid[p.y()][p.x()] = GridValues.SEARCHED;
    }

    static GridValues getOccupancy(Point p){
        return grid[p.y()][p.x()];
    }
}
