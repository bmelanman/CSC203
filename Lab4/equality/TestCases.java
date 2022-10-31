import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCases {
    @Test
    public void testExercise1() {
        final CourseSection one = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 40), LocalTime.of(11, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 40), LocalTime.of(11, 0));

        assertEquals(one, two);
        assertEquals(two, one);
    }

    @Test
    public void testExercise2() {
        final CourseSection one = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
                LocalTime.of(1, 10), LocalTime.of(2, 0));

        assertNotEquals(one, two);
        assertNotEquals(two, one);
    }

    @Test
    public void testExercise3() {
        final CourseSection one = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 40), LocalTime.of(11, 0));
        final CourseSection two = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 40), LocalTime.of(11, 0));

        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void testExercise4() {
        final CourseSection one = new CourseSection("CSC", "203", 35,
                LocalTime.of(9, 10), LocalTime.of(10, 0));
        final CourseSection two = new CourseSection("CSC", "203", 34,
                LocalTime.of(9, 10), LocalTime.of(10, 0));

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
   public void studentTest() {
       final CourseSection one = new CourseSection("CSC", "203", 35,
               LocalTime.of(9, 10), LocalTime.of(10, 0));
       final CourseSection two = new CourseSection("CPE", "442", 25,
               LocalTime.of(10, 10), LocalTime.of(12, 0));

       ArrayList<CourseSection> courses1 = new ArrayList<>();
       courses1.add(one);
       courses1.add(two);

       final Student student1 = new Student("Doe", "Jane", 21, courses1);
       Student student2 = new Student("Doe", "Jane", 21, courses1);

       assertEquals(student1, student2);
       assertEquals(student1.hashCode(), student2.hashCode());

       final CourseSection three = new CourseSection("CPE", "442", 25,
               LocalTime.of(10, 10), LocalTime.of(12, 0));

       ArrayList<CourseSection> courses2 = new ArrayList<>();
       courses2.add(one);
       courses2.add(two);
       courses2.add(three);

       student2 = new Student("Doe", "Jane", 21, courses2);

       assertNotEquals(student1, student2);
       assertNotEquals(student1.hashCode(), student2.hashCode());
    }
}
