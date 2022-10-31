public class Guest implements Person{
    private boolean atEvent;
    private double threshold;

    public Guest(boolean atEvent, double threshold) {
        this.atEvent = atEvent;
        this.threshold = threshold;
    }

    public void arrive() {
        atEvent = true;
        System.out.println("Where's the food?");
    }

    public void timeToGo(double time) {
        if (time > threshold)
            atEvent = false;
    }

}