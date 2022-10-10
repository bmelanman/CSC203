public class CoffeeOrder {
    private boolean iced;
    private int sweetness;
 
    public CoffeeOrder(boolean i, int s) {
       iced = i;
       sweetness = s;
    }
    public boolean getIced() { return iced; }
    public int getSweetness() { return sweetness; }
    
 }