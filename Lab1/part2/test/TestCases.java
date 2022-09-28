import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TestCases {

    @Test
    public void test() {
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("Intro to CS", 100),
                new CourseGrade("Data Structures", 95),
                new CourseGrade("Algorithms", 91),
                new CourseGrade("Computer Organization", 91),
                new CourseGrade("Operating Systems", 75),
                new CourseGrade("Non-CS", 83));
        Applicant testApplicant = new Applicant("Aakash", grades, "Student", 8);

        assertEquals("Aakash", testApplicant.getName());
        assertEquals(grades, testApplicant.getGrades());
        assertEquals(grades.get(2), testApplicant.getGradeFor("Algorithms"));
        assertEquals(null, testApplicant.getGradeFor("Bowling"));

        assertEquals("Student", testApplicant.getBackground());
        assertEquals(8, testApplicant.getShoeSize(), 0.1);
    }

    @Test
    public void test2() {

        List<CourseGrade> grades1 = Arrays.asList(
                new CourseGrade("Intro to CS", 100),
                new CourseGrade("Data Structures", 95),
                new CourseGrade("Algorithms", 91),
                new CourseGrade("Computer Organization", 99),
                new CourseGrade("Operating Systems", 98),
                new CourseGrade("Non-CS", 102));
        Applicant applicantA = new Applicant("Karthik", grades1, "Student", 13.5);

        List<CourseGrade> grades2 = Arrays.asList(
                new CourseGrade("Intro to CS", 73),
                new CourseGrade("Data Structures", 85),
                new CourseGrade("Algorithms", 81),
                new CourseGrade("Computer Organization", 69),
                new CourseGrade("Operating Systems", 51),
                new CourseGrade("Non-CS", 98));
        Applicant applicantB = new Applicant("Laura", grades2, "Student", 8);

        System.out.println(SimpleIf.average(applicantB.getGrades()));

        assertEquals(false, SimpleIf.analyzeApplicant2(applicantA, 75));
        assertEquals(true, SimpleIf.analyzeApplicant2(applicantB, 75));
    }
}
