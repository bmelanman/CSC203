import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

public class TestCases {
    public static final double DELTA = 0.00001;

    /* some sample tests but you must write more! see lab write up */

    @Test
    public void testCircleGetArea() {
        Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

        assertEquals(101.2839543, c.getArea(), DELTA);
    }

    @Test
    public void testCircleGetPerimeter() {
        Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

        assertEquals(35.6759261, c.getPerimeter(), DELTA);
    }

    @Test
    public void testWorkSpaceAreaOfAllShapes() {
        WorkSpace ws = new WorkSpace();

        ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
        ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
        ws.add(new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0),
                Color.BLACK));

        assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
    }

    @Test
    public void testWorkSpaceGetCircles() {
        WorkSpace ws = new WorkSpace();
        List<Circle> expected = new LinkedList<>();

        // Have to make sure the same objects go into the WorkSpace as
        // into the expected List since we haven't overriden equals in Circle.
        Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
        Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

        ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
        ws.add(c1);
        ws.add(new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0),
                Color.BLACK));
        ws.add(c2);

        expected.add(c1);
        expected.add(c2);

        // Doesn't matter if the "type" of lists are different (e.g Linked vs
        // Array). List equals only looks at the objects in the List.
        assertEquals(expected, ws.getCircles());
    }

    /*
     * HINT - comment out implementation tests for the classes that you have not
     * yet implemented
     */
    @Test
    public void testCircleImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getColor", "setColor", "getArea", "getPerimeter", "translate",
                "getRadius", "setRadius", "getCenter", "equals");

        final List<Class> expectedMethodReturns = Arrays.asList(
                Color.class, void.class, double.class, double.class, void.class,
                double.class, void.class, Point.class, boolean.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[] { Color.class }, new Class[0], new Class[0], new Class[] { Point.class },
                new Class[0], new Class[] { double.class }, new Class[0], new Class[] { Object.class });

        verifyImplSpecifics(Circle.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
    }

    @Test
    public void testRectangleImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getColor", "setColor", "getArea", "getPerimeter", "translate",
                "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

        final List<Class> expectedMethodReturns = Arrays.asList(
                Color.class, void.class, double.class, double.class, void.class,
                double.class, void.class, double.class, void.class, Point.class, boolean.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[] { Color.class }, new Class[0], new Class[0], new Class[] { Point.class },
                new Class[0], new Class[] { double.class }, new Class[0], new Class[] { double.class },
                new Class[0], new Class[] { Object.class });

        verifyImplSpecifics(Rectangle.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
    }

    @Test
    public void testTriangleImplSpecifics()
            throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList(
                "getColor", "setColor", "getArea", "getPerimeter", "translate",
                "getVertexA", "getVertexB", "getVertexC", "equals");

        final List<Class> expectedMethodReturns = Arrays.asList(
                Color.class, void.class, double.class, double.class, void.class,
                Point.class, Point.class, Point.class, boolean.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(
                new Class[0], new Class[] { Color.class }, new Class[0], new Class[0], new Class[] { Point.class },
                new Class[0], new Class[0], new Class[0], new Class[] { Object.class });

        verifyImplSpecifics(Triangle.class, expectedMethodNames,
                expectedMethodReturns, expectedMethodParameters);
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

        assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(), "Invalid test configuration");
        assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
                "Invalid test configuration");

        for (int i = 0; i < expectedMethodNames.size(); i++) {
            Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
                    expectedMethodParameters.get(i));
            assertEquals(expectedMethodReturns.get(i), method.getReturnType());
        }
    }

    @Test
    public void testTriangle(){
        Point a = new Point(-4, -1);
        Point b = new Point(-1, 4);
        Point c = new Point(3, -4);
        Color color = new Color(255);
        
        Triangle triangle = new Triangle(a, b, c, color);
        Triangle triangle2 = new Triangle(a, b, c, color);

        // Test .equals()
        assertEquals(triangle.equals(triangle2), true);
        assertEquals(triangle.equals(triangle), true);
        assertEquals(triangle.equals(null), false);
        assertEquals(triangle.equals(a), false);

        // Test Area and Perimeter methods
        assertEquals(triangle.getArea(), 22);
        assertEquals(triangle.getPerimeter(), 22.390996910708367);

        //Test translate
        triangle.translate(c);

        // Values shouldn't change because of translate
        assertEquals(triangle.getArea(), 22);
        assertEquals(triangle.getPerimeter(), 22.390996910708367);

        // Test color
        assertEquals(triangle.getColor().getRGB(), -16776961);

        Color new_color = new Color(1);
        triangle.setColor(new_color);

        assertEquals(triangle.getColor().getRGB(), -16777215);
    }

    @Test
    public void testCircle(){
        Point center = new Point(-2, 1);
        double radius = 1.323;
        Color color = new Color(255);
        
        Circle circle = new Circle(radius, center, color);
        Circle circle2 = new Circle(radius, center, color);

        // Test .equals()
        assertEquals(circle.equals(circle2), true);
        assertEquals(circle.equals(circle), true);
        assertEquals(circle.equals(null), false);
        assertEquals(circle.equals(center), false);

        // Test Area and Perimeter methods
        assertEquals(circle.getArea(), 5.498820727765168);
        assertEquals(circle.getPerimeter(), 8.312654161398592);

        //Test translate
        circle.translate(center);

        // Values shouldn't change because of translate
        assertEquals(circle.getArea(), 5.498820727765168);
        assertEquals(circle.getPerimeter(), 8.312654161398592);

        // Test color
        assertEquals(circle.getColor().getRGB(), -16776961);

        Color new_color = new Color(1);
        circle.setColor(new_color);

        assertEquals(circle.getColor().getRGB(), -16777215);
    }

    @Test
    public void testRectangle(){
        double width = 3.76;
        double height = 1.44;
        Point topLeft = new Point(2, -5);
        Color color = new Color(255);
        
        Rectangle rectangle = new Rectangle(width, height, topLeft, color);
        Rectangle rectangle2 = new Rectangle(width, height, topLeft, color);

        // Test .equals()
        assertEquals(rectangle.equals(rectangle2), true);
        assertEquals(rectangle.equals(rectangle), true);
        assertEquals(rectangle.equals(null), false);
        assertEquals(rectangle.equals(topLeft), false);

        // Test Area and Perimeter methods
        assertEquals(rectangle.getArea(), 5.4144);
        assertEquals(rectangle.getPerimeter(), 10.4, DELTA);

        //Test translate
        rectangle.translate(topLeft);

        // Values shouldn't change because of translate
        assertEquals(rectangle.getArea(), 5.4144);
        assertEquals(rectangle.getPerimeter(), 10.4, DELTA);

        // Test color
        assertEquals(rectangle.getColor().getRGB(), -16776961);

        Color new_color = new Color(1);
        rectangle.setColor(new_color);

        assertEquals(rectangle.getColor().getRGB(), -16777215);
    }

    @Test
    public void testWorkSpace(){
        WorkSpace workspace = new WorkSpace();

        Rectangle rect_black = new Rectangle(1.4, 9.8, new Point(5, -2), new Color(0));
        Circle circ_white = new Circle(2.222, new Point(1, 1), new Color(255));
        Triangle tri_gray = new Triangle(new Point(4, 9), new Point(11, -9), new Point(0, 0), new Color((int) 255/2));
        Circle circ_black = new Circle(9.1, new Point(0, -3), new Color(0));

        workspace.add(rect_black);
        workspace.add(circ_white);
        workspace.add(tri_gray);
        workspace.add(circ_black);

        assertEquals(workspace.size(), 4);
        assertEquals(workspace.get(2).equals(tri_gray), true);
        assertEquals(workspace.getCircles().size(), 2);

        assertEquals(workspace.getAreaOfAllShapes(), 356.88622278685716);
        assertEquals(workspace.getPerimeterOfAllShapes(), 136.91296016906324);
    }
}
