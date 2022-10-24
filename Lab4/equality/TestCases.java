import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import java.time.LocalTime;

public class TestCases
{
   @Test
   public void testExercise1()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one, two);
      assertEquals(two, one);
   }

   @Test
   public void testExercise2()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(1, 10), LocalTime.of(2, 0));

      assertNotEquals(one, two);
      assertNotEquals(two, one);
   }

   @Test
   public void testExercise3()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testExercise4()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 34,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }
}
