import java.util.Objects;

public class Professor extends Person {
    private double mortgage;
    private final boolean tenure;

    public Professor(double mortgage, boolean tenure, String name, double accountBalance, int legs) {
        super(name, accountBalance, legs);
        this.mortgage = mortgage;
        this.tenure = tenure;
    }

    public String getTenure() {
        if (tenure) {
            return "tenure";
        }
        return "no tenure";
    }

    @Override
    public void getPaid(double paycheck) {
        double mortgage_payment = 0;

        if (this.mortgage > 0) {
            mortgage_payment = (paycheck / 4);
            this.mortgage -= mortgage_payment;
        }
        this.accountBalance += paycheck - mortgage_payment;
    }

    @Override
    public String toString() {
        return super.toString() + " and a Professor object with a " + this.mortgage +
                " dollar mortgage and " + getTenure();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Double.compare(professor.mortgage, mortgage) == 0 && tenure == professor.tenure;
    }
}