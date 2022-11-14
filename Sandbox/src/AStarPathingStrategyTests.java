/**
 * JUnit tests for Sorts assignment.
 *
 * @author Paul Hatalsky
 * @version 11/17/2017 Developed for CPE 203 A* testing
 */

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AStarPathingStrategyTests {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.print("Starting: " + description.getMethodName() + "...");
        }
    };

    @Rule
    public Stopwatch sw = new Stopwatch() {
        protected void finished(long nanos, Description description) {
            System.out.println(" (" + runtime(TimeUnit.MILLISECONDS) + " ms)");
        }

        protected void succeeded(long nanos, Description description) {
            System.out.print("Passed");
        }

        protected void failed(long nanos, Throwable e, Description description) {
            System.out.print("Failed");
        }
    };

    private static final PathingStrategy strategy = new AStarPathingStrategy();
    private static GridValues[][] grid;
    private static final int ROWS = 9;
    private static final int COLS = 9;

    private enum GridValues {BACKGROUND, OBSTACLE, GOAL}

    private static Field pointX;
    private static Field pointY;

    @BeforeClass
    public static void before() {
        grid = new GridValues[ROWS][COLS];

        try {
            /* Nice security you've got there, Oracle. */
            pointX = Point.class.getDeclaredField("x");
            pointX.setAccessible(true);
            pointY = Point.class.getDeclaredField("y");
            pointY.setAccessible(true);
        } catch (NoSuchFieldException e) {
            /* All bets are off if they didn't name their fields "x" and "y". But
             * we were already assuming that anyway. */
        }
    }

    private static int getX(Point p) {
        try {
            return (int) pointX.get(p);
        } catch (IllegalAccessException e) {
            return -1;
        }
    }

    private static int getY(Point p) {
        try {
            return (int) pointY.get(p);
        } catch (IllegalAccessException e) {
            return -1;
        }
    }

    private static void initialize_grid() {
        for (GridValues[] gridValues : grid) {
            Arrays.fill(gridValues, GridValues.BACKGROUND);
        }
        grid[1][3] = GridValues.OBSTACLE;
        for (int row = 1; row < 6; row++) {
            grid[row][4] = GridValues.OBSTACLE;
        }
        grid[5][2] = GridValues.OBSTACLE;
        grid[5][3] = GridValues.OBSTACLE;
    }

    private static boolean withinBounds(Point p, GridValues[][] grid) {
        return getY(p) >= 0 && getY(p) < grid.length &&
                getX(p) >= 0 && getX(p) < grid[0].length;
    }

    private static boolean neighbors(Point p1, Point p2) {
        return getX(p1) + 1 == getX(p2) && getY(p1) == getY(p2) ||
                getX(p1) - 1 == getX(p2) && getY(p1) == getY(p2) ||
                getX(p1) == getX(p2) && getY(p1) + 1 == getY(p2) ||
                getX(p1) == getX(p2) && getY(p1) - 1 == getY(p2);
    }

    @Test(timeout = 500)
    public void test01_computePath01() {

        initialize_grid();

        List<Point> path =
                strategy.computePath(
                        new Point(0, 0),
                        new Point(0, 2),
                        p -> withinBounds(p, grid) && grid[getY(p)][getX(p)] != GridValues.OBSTACLE,
                        AStarPathingStrategyTests::neighbors,
                        PathingStrategy.CARDINAL_NEIGHBORS_RDLU
                );

        Point[] expected = new Point[]{new Point(0, 1)};

        assertArrayEquals(expected, path.toArray());
    }

}