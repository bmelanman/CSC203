import java.util.Objects;

public class Person extends Animal {
    private String name;
    protected double accountBalance;

    public double getAccountBalance() {
        return accountBalance;
    }

    public Person(String name, double accountBalance, int legs) {
        super(legs);
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public void getPaid(double paycheck) {
        this.accountBalance += paycheck;
    }

    @Override
    public String toString() {
        return super.toString() + "and a Person object whose name is " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Double.compare(person.accountBalance, accountBalance) == 0 && Objects.equals(name, person.name);
    }
}