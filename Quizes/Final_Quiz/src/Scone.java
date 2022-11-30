public class Scone extends Food {
    private final int a;

    public Scone(String symbol, int pop, int a) {
        super(symbol, pop);
        this.a = a;
    }

    @Override
    public void display() {
        for (int i = 1; i <= a; i++) {

            for (int j = 0; j < a - i; j++)
                System.out.print(" ");

            for (int j = 0; j < (2 * i - 1); j++)
                System.out.print(getSymbol());

            System.out.println();
        }
    }

    @Override
    public String toString() {
        // return "I am [popular] points popular and a = [a]"
        return super.toString() + "a = " + a;
    }
}
