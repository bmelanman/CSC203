import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {
    public static final double DELTA = 0.00001;
    private final Bindings bindings = new VariableBindings();

    @Before
    public void init() {
        bindings.addBinding("x", 2.5);
        bindings.addBinding("y", 10);
    }

    @Test
    public void test01_AddExpressionEvaluate() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        AddExpression add = new AddExpression(x, y);

        assertEquals(12.5, add.evaluate(bindings), DELTA);
    }

    @Test
    public void test02_AddExpressionToString() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        AddExpression add = new AddExpression(x, y);

        assertEquals("(x + y)", add.toString());
    }

    @Test
    public void test03_SubtractExpressionEvaluate() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        SubtractExpression sub = new SubtractExpression(x, y);

        assertEquals(-7.5, sub.evaluate(bindings), DELTA);

    }

    @Test
    public void test04_SubtractExpressionToString() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        SubtractExpression sub = new SubtractExpression(x, y);

        assertEquals("(x - y)", sub.toString());
    }

    @Test
    public void test05_MultiplyExpressionEvaluate() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        MultiplyExpression mul = new MultiplyExpression(x, y);

        assertEquals(25, mul.evaluate(bindings), DELTA);

    }

    @Test
    public void test06_MultiplyExpressionToString() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        MultiplyExpression mul = new MultiplyExpression(x, y);

        assertEquals("(x * y)", mul.toString());
    }

    @Test
    public void test07_DivideExpressionEvaluate() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        DivideExpression div = new DivideExpression(x, y);

        assertEquals(0.25, div.evaluate(bindings), DELTA);

    }

    @Test
    public void test08_DivideExpressionToString() {
        IdentifierExpression x = new IdentifierExpression("x");
        IdentifierExpression y = new IdentifierExpression("y");
        DivideExpression div = new DivideExpression(x, y);

        assertEquals("(x / y)", div.toString());
    }
}