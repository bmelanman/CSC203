
public class CourseGrade {
    private String courseName;
    private int score; 

    public CourseGrade(String courseName, int score) {
      this.courseName = courseName;
      this.score = score;
    }
  
    public String getCourseName() {
      return this.courseName;
    }
  
    public int getScore() {
      return this.score;
    }
  }
  