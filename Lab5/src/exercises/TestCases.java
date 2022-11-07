import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.*;

import static org.junit.Assert.assertEquals;

public class TestCases {
    /* helper methods used in the tests below */
    private <T> List<T> mapIt(List<T> list, Function<T, T> func) {
        final List<T> results = new LinkedList<>();
        for (final T value : list) {
            results.add(func.apply(value));
        }

        return results;
    }

    private <T> List<T> filterIt(List<T> list, Predicate<T> pred) {
        final List<T> results = new LinkedList<>();
        for (final T value : list) {
            if (pred.test(value)) {
                results.add(value);
            }
        }

        return results;
    }

    private LongSupplier getNumberGenerator() {
        int[] number = {0};

        // Increments a variable stored in a function tied to a variable? Pretty neat but more info needed
        return () -> number[0]++;
    }

    private LongFunction<LongUnaryOperator> createAdder() {
        return x -> y -> x + y;
    }

    @Test
    public void testExercise1() {

        // Lambda function increases x by 1
        final LongUnaryOperator func = x -> x + 1;

        // 7 -> 7 + 1 = 8
        assertEquals(8, func.applyAsLong(7));
    }

    @Test
    public void testExercise2() {
        final LongSupplier first = getNumberGenerator();
        final LongSupplier second = getNumberGenerator();

        assertEquals(0, first.getAsLong());
        assertEquals(1, first.getAsLong());
        assertEquals(0, second.getAsLong());
        assertEquals(2, first.getAsLong());
        assertEquals(1, second.getAsLong());
        assertEquals(3, first.getAsLong());
        assertEquals(2, second.getAsLong());
    }

    @Test
    public void testExercise3() {
        final LongFunction<LongUnaryOperator> curried = createAdder();

        // Functions add a value to a given operand?
        final LongUnaryOperator add7 = curried.apply(7);
        final LongUnaryOperator add3 = curried.apply(3);

        assertEquals(9, add7.applyAsLong(2));
        assertEquals(5, add3.applyAsLong(2));
        assertEquals(13, add3.applyAsLong(10));
    }

    @Test
    public void testExercise4() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> expected = Arrays.asList(11, 12, 13, 14, 15);
        final int n = 10;

        // Iterates through the list and adds n to each element
        final List<Integer> result = mapIt(numbers, x -> x + n);

        assertEquals(expected, result);
    }

    @Test
    public void testExercise5() {
        final List<String> strings = Arrays.asList(
                "hello",
                "Hello",
                "HeLLo",
                "helLo",
                "HELLO"
        );

        final List<String> expected = Arrays.asList(
                "hello",
                "hello",
                "hello",
                "hello",
                "hello"
        );

        // Applies a given rule to an object, in this case it converts to lower case
        final List<String> result = mapIt(strings, String::toLowerCase);

        assertEquals(expected, result);
    }

    @Test
    public void testExercise6() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> expected = Arrays.asList(2, 4);

        // Filters objects that apply to a given rule or function,
        // in this case it filters out every odd number using binary math
        final List<Integer> result = filterIt(numbers, x -> (x & 1) == 0);

        assertEquals(expected, result);
    }

    @Test
    public void testExercise7() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> expected = Arrays.asList(4, 16);

        // Filters odd numbers and then squares every remaining value
        final List<Integer> result = mapIt(
                filterIt(numbers, x -> (x & 1) == 0),
                x -> x * x
        );

        assertEquals(expected, result);
    }
}
