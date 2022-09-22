
public class BetterLoop {
  /**
   * Accept an applicant if they have at least 4 grades above 85. Their non-CS
   * GPA counts as a grade in this case.
   * 
   * @param scores The applicant's list of scores
   * @return true if the applicant meets the requirements
   */
  public static boolean atLeastFourOver85(int[] scores) {
    int count = 0;

    for (int score : scores){
        if (score >= 85){
            count++;
        }
    }

    if (count >= 4){
        return true;
    }

    return false;
  }

  /**
   * Compute an applicant's average score in their 5 CS courses (that is, you must
   * NOT consider the final item in the array, the non-CS GPA).
   * 
   * @param scores
   * @return The average score
   */
  public static double average(int[] scores) {
    int list_len = scores.length;
    double sum = 0;

    for (int i = 0; i < list_len; i++){
        sum += scores[i];
    }

    return sum / list_len;
  }
}
