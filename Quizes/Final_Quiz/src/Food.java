public abstract class Food {
    private final String symbol;
    private int popularity;

    public Food(String symbol, int popularity) {
        this.symbol = symbol;
        this.popularity = popularity;
    }

    public String getSymbol() {
        return symbol;
    }

    // Abstract Methods
    abstract void display();

    // Public Methods
    public void boost() {
        popularity++;

        if (popularity < 5) {
            System.out.println(":(");
        } else {
            System.out.println("Nice!");
        }
    }

    @Override
    public String toString(){
        return "I am " + popularity + " points popular and ";
    }
}
