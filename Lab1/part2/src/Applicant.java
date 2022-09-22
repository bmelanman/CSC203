import java.util.*;

public class Applicant {
    private String name;
    private List<CourseGrade> grades;
    private String background;
    private double shoe_size;

    public Applicant(String name, List<CourseGrade> grades, String background, double shoe_size){
        this.name = name;
        this.grades = grades;
        this.background = background;
        this.shoe_size = shoe_size;
    }

    public String getName(){
        return this.name;
    }

    public List<CourseGrade> getGrades(){
        return this.grades;
    }

    public String getBackground(){
        return this.background;
    }

    public double getShoeSize(){
        return this.shoe_size;
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
