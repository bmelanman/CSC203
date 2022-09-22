import java.util.*;

public class Applicant {
    private String name;
    private List<CourseGrade> grades;

    public Applicant(String name, List<CourseGrade> grades){
        this.name = name;
        this.grades = grades;
    }

    public String getName(){
        return this.name;
    }

    public List<CourseGrade> getGrades(){
        return this.grades;
    }

    public CourseGrade getGradeFor(String course){
        for (CourseGrade courseGrade : this.grades){
            if (courseGrade.getCourseName().equals(course)){
                return courseGrade;
            }
        }

        return null;
    }
}
