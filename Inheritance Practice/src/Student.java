public class Student extends Person {
    private final double gpa;
    private double studentLoans;

    public Student(double gpa, double studentLoans, String name, double accountBalance, int legs) {
        super(name, accountBalance, legs);
        this.gpa = gpa;
        this.studentLoans = studentLoans;
    }

    @Override
    public String toString() {
        return super.toString() + "and a Student object with a " + this.gpa +
                " gpa and " + this.studentLoans +
                " dollars of student loans.";
    }

    @Override
    public void getPaid(double paycheck) {
        double loan_payment = 0;

        if (this.studentLoans > 0) {
            loan_payment = (paycheck / 10);
            this.studentLoans -= loan_payment;
        }
        this.accountBalance += paycheck - loan_payment;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(student.gpa, gpa) == 0 && Double.compare(student.studentLoans, studentLoans) == 0;
    }
}