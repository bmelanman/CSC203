public class Vendor implements Person {
    private double hoursWorked;
    private double pricePerHour;

    public Vendor(double hoursWorked, double pricePerHour) {
        this.hoursWorked = hoursWorked;
        this.pricePerHour = pricePerHour;
    }

    public void arrive() {
        hoursWorked = 0;
        System.out.println("Here is the food.");
    }

    public double calculatePayment(double tip) {
        return tip + hoursWorked * pricePerHour;
    }
}