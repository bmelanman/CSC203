import java.awt.*;

public class Place {
    private String name;
    private Point location;

    public Place(String n, Point p) {
        name = n;
        location = p;
    }

    public boolean equals(Place input) {
        if (input == null) { // 1
            return false;
        }
        if (input.getClass() != this.getClass()) { // 2
            return false;
        }

        Place p = (Place) input; // 3

        return (name.equals(p.name) == true) && (location.equals(p.location) == true); // 4
    }

}