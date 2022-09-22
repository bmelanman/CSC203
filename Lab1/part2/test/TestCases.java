import static org.junit.Assert.*;

// import java.lang.reflect.Method;
// import java.lang.reflect.Modifier;
// import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TestCases {
    
    @Test
    public void test() {
        // This will not compile until you implement the Applicant class
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("Intro to CS", 100),
                new CourseGrade("Data Structures", 95),
                new CourseGrade("Algorithms", 91),
                new CourseGrade("Computer Organization", 91),
                new CourseGrade("Operating Systems", 75),
                new CourseGrade("Non-CS", 83));
        Applicant testApplicant = new Applicant("Aakash", grades);

        assertEquals("Aakash", testApplicant.getName());
        assertEquals(grades, testApplicant.getGrades());
        assertEquals(grades.get(2), testApplicant.getGradeFor("Algorithms"));
        assertEquals(null, testApplicant.getGradeFor("Bowling"));


    }
}
