import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PartTwoTestCases {
    public static final double DELTA = 0.00001;

    @Test
    public void testCircleImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getCenter", "getRadius", "perimeter");

        final List<Class> expectedMethodReturns = Arrays.asList(
                Point.class, double.class, double.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Circle.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
    }

    @Test
    public void testRectangleImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getTopLeft", "getBottomRight", "perimeter");

        final List<Class> expectedMethodReturns = Arrays.asList(
                Point.class, Point.class, double.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Rectangle.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
    }

    @Test
    public void testPolygonImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getPoints", "perimeter");

        final List<Class> expectedMethodReturns = Arrays.asList(
                List.class, double.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[0]);

        verifyImplSpecifics(Polygon.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
    }

    @Test
    public void testCircle() {
        Point center = new Point(1.0, 1.0);
        double radius = 2.0;

        Circle circle = new Circle(center, radius);

        assertEquals(4 * Math.PI, circle.perimeter(), DELTA);
    }

    @Test
    public void testRectangle() {
        Point top_left = new Point(-1.0, 2.0);
        Point bottom_right = new Point(1.0, -1.6);

        Rectangle rectangle = new Rectangle(top_left, bottom_right);

        assertEquals(11.2, rectangle.perimeter(), DELTA);
    }

    @Test 
    public void testPolygon(){
        List<Point> points = new ArrayList < Point >(); 
        points.add(new Point(0, 0));
        points.add(new Point(3,1));
        points.add(new Point(1,4));
        points.add(new Point(-1,4));

        Polygon polygon = new Polygon(points);

        assertEquals(10.89093456125003, polygon.perimeter(), DELTA);
    }

    @Test
    public void testBigger(){
        Point circle_point = new Point(1.0, 1.0);
        double radius = 2.0;

        Point top_left = new Point(-1.0, 2.0);
        Point bottom_right = new Point(1.0, -1.6);

        List<Point> points = new ArrayList < Point >(); 
        points.add(new Point(0, 0));
        points.add(new Point(3,1));
        points.add(new Point(1,4));
        points.add(new Point(-1,4));

        Rectangle rectangle = new Rectangle(top_left, bottom_right);
        Circle circle = new Circle(circle_point, radius);
        Polygon polygon = new Polygon(points);

        assertEquals(4 * Math.PI, Bigger.whichIsBigger(circle, rectangle, polygon));
    }

    private static void verifyImplSpecifics(
            final Class<?> clazz,
            final List<String> expectedMethodNames,
            final List<Class> expectedMethodReturns,
            final List<Class[]> expectedMethodParameters)
            throws NoSuchMethodException {
        assertEquals(0, clazz.getFields().length,
                "Unexpected number of public fields");

        final List<Method> publicMethods = Arrays.stream(
                clazz.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .collect(Collectors.toList());

        assertEquals(expectedMethodNames.size(), publicMethods.size(),
                "Unexpected number of public methods");

        assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(),
                "Invalid test configuration");
        assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
                "Invalid test configuration");

        for (int i = 0; i < expectedMethodNames.size(); i++) {
            Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
                    expectedMethodParameters.get(i));
            assertEquals(expectedMethodReturns.get(i), method.getReturnType());
        }
    }
}
