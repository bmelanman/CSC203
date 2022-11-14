import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class PathingMain extends PApplet {
    private PImage wyvern;
    private PImage background;
    private PImage obstacle;
    private PImage goal;
    private List<Point> path;
    private static final int TILE_SIZE = 32;
    private static GridValues[][] grid;
    private static final int ROWS = 15;
    private static final int COLS = 20;
    enum GridValues {BACKGROUND, OBSTACLE, GOAL, SEARCHED, DEAD_END}
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
        draw_path();

        image(wyvern, wPos.x * TILE_SIZE, wPos.y * TILE_SIZE);
    }

    private void draw_grid() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                draw_tile(row, col);
            }
        }
    }

    private void draw_path() {
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
        draw_visited();
    }

    private void draw_tile(int row, int col) {
        switch (grid[row][col]) {
            case BACKGROUND, SEARCHED, DEAD_END -> image(background, col * TILE_SIZE, row * TILE_SIZE);
            case OBSTACLE -> image(obstacle, col * TILE_SIZE, row * TILE_SIZE);
            case GOAL -> image(goal, col * TILE_SIZE, row * TILE_SIZE);
        }
    }

    private void draw_visited(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if (grid[i][j] == GridValues.SEARCHED || grid[i][j] == GridValues.DEAD_END){
                    fill(0, 128);
                    rect(
                            j * TILE_SIZE + (float) (TILE_SIZE / 4),
                            i * TILE_SIZE + (float) (TILE_SIZE / 4),
                            (float) (TILE_SIZE / 2),
                            (float) (TILE_SIZE / 2)
                    );
                }
            }
        }
    }

    private void clear_searched(GridValues[][] grid){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if (grid[i][j] == GridValues.SEARCHED || grid[i][j] == GridValues.DEAD_END){
                    grid[i][j] = GridValues.BACKGROUND;
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("PathingMain");
    }

    public void keyPressed() {
        if (key == ' ') {
            path.clear();
            clear_searched(grid);
            foundPath = findGoal(wPos, goalPos, grid, path);
            redraw();
        }
        else if (key == 'p'){
            draw_visited();
            redraw();
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

    private boolean findGoal(Point pos, Point goal, GridValues[][] grid, List<Point> path) {

        PathingStrategy strategy = new DepthFirstSearch();

        Predicate<Point> canTraverse =
                (p) -> (withinBounds(p, grid)
                        && getOccupancy(p) != PathingMain.GridValues.OBSTACLE
                        && getOccupancy(p) != PathingMain.GridValues.DEAD_END);

        List<Point> aStarPath = strategy.computePath(
                pos,
                goal,
                canTraverse,
                Point::neighbors,
                PathingStrategy.CARDINAL_NEIGHBORS_RDLU
        );

        if (aStarPath.size() == 0) {
            System.out.println("No path found");
            return false;
        }

        path.addAll(aStarPath);

        return true;
    }

    private static boolean withinBounds(Point p, GridValues[][] grid) {
        return p.y >= 0 && p.y < grid.length && p.x >= 0 && p.x < grid[0].length;
    }

    public static void setOccupancy(Point p, GridValues value) {
        grid[p.y][p.x] = value;
    }

    public static GridValues getOccupancy(Point p){
        return grid[p.y][p.x];
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
