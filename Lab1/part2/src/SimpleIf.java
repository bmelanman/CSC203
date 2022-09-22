import java.util.List;

public class SimpleIf {

    /**
     * Takes an applicant's average score and accepts the applicant if the average
     * is higher than 85.
     * 
     * @param avg       The applicant's average score
     * @param threshold The threshold score
     * @return true if the applicant's average is over the threshold, and false
     *         otherwise
     */
    public static boolean analyzeApplicant(double avg, double threshold) {

        if (avg > threshold) {
            return true;
        }

        return false;
    }

    public static boolean analyzeApplicant2(Applicant applicant, double threshold) {
        double class_avg = average(applicant.getGrades());

        // Multiple if statemnets have been used to improve readability
        if (class_avg <= threshold) {
            // Ethical
            return false;
        } else if (applicant.getBackground().equals("Criminal")) {
            // Ethical
            return false;
        } else if (applicant.getShoeSize() > 12) {
            // Unethical (?)
            return false;
        }

        return true;
    }

    public static double average(List<CourseGrade> grades) {
        int list_len = grades.size();
        int sum = 0;

        for (CourseGrade courseGrade : grades){
            sum += courseGrade.getScore();
        }

        return sum / list_len;
    }

    /**
     * Takes two applicants' average scores and returns the score of the applicant
     * with the higher average.
     * 
     * @param avg1 The first applicant's average score
     * @param avg2 The second applicant's average score
     * @return the higher average score
     */
    public static double maxAverage(double avg1, double avg2) {

        if (avg1 >= avg2) {
            return avg1;
        }

        return avg2;
    }

}
