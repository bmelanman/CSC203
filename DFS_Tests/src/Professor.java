public class Professor {

    private final boolean hasTenure;
    private final int mortgage;

    public Professor(int mortgage, boolean hasTenure, String name, int a, int b) {
        this.hasTenure = hasTenure;
        this.mortgage = mortgage;
    }

    public int getMortgage() {
        return mortgage;
    }

    public boolean checkTenure() {
        return hasTenure;
    }
}
