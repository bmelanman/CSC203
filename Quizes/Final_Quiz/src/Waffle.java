public class Waffle extends Food {
    public int length;
    public int height;

    public Waffle(String symbol, int pop, int l, int h) {
        super(symbol, pop);
        length = l;
        height = h;
    }

    @Override
    public void display() {
        for (int i = 0; i < length; i++)
            System.out.print(getSymbol());
        System.out.println();
        int h = 1;
        while (h < height - 1) {
            for (int i = 0; i < length; i++)
                if (i == 0 || i == length - 1)
                    System.out.print(getSymbol());
                else
                    for (int l = 0; l < getSymbol().length(); l++)
                        System.out.print(" ");

            System.out.println();
            h++;
        }
        for (int i = 0; i < length; i++)
            System.out.print(getSymbol());
        System.out.println();
    }

    @Override
    public String toString() {
        // return "I am [popular] points popular and [height] tall "
        return super.toString() + height + " tall";
    }
}
