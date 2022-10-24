public class Animal {
    private final int numLegs;

    public Animal(int legs) {
        numLegs = legs;
    }

    public String toString() {
        return "I am an Animal object with " + numLegs + " legs";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        return this.numLegs == ((Animal) o).numLegs;
    }
}

