import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        ArrayList<Professor> profs = new ArrayList<>();
        profs.add(new Professor(500000, false, "Julie", 0, 2));
        profs.add(new Professor(300000, false, "Paul", 0, 2));
        profs.add(new Professor(250001, true, "Phil", 0, 2));
        profs.add(new Professor(250000, true, "Hugh", 0, 2));
        profs.add(new Professor(200000, false, "Kurt", 0, 2));

        // TODO:
        // Write one line of code to calculate the average mortgage of all professors with tenure.

        double avgMortgage = profs.stream()
                .filter(Professor::checkTenure)
                .mapToInt(Professor::getMortgage)
                .average()
                .orElse(-1);

        System.out.println("avg: " + avgMortgage);
    }

}

interface Orbits {
    double duration();

    CelestialBody orbiting();
}

// Override the equals() method from Object in both CelestialBody and Planet.  Indicate which method should go in which class.
class CelestialBody {
    private final double mass;  // in kg
    private final double velocity;
    private final String name;

    public final Comparator<CelestialBody> comp1 = Comparator.comparingDouble(CelestialBody::mass).reversed();
    public final Comparator<CelestialBody> comp2 = Comparator.comparing(CelestialBody::mass).reversed();

    public CelestialBody(double mass, double velocity, String name) {
        this.mass = mass;
        this.velocity = velocity;
        this.name = name;
    }

    public double mass() {
        return mass;
    }

    public double velocity() {
        return velocity;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CelestialBody otherBody = (CelestialBody) o;

        return Double.compare(otherBody.mass, mass) == 0 &&
                Double.compare(otherBody.velocity, velocity) == 0 &&
                Objects.equals(name, otherBody.name);
    }
}

class Planet extends CelestialBody implements Orbits {

    private final double orbitalDuration;
    private final CelestialBody bodyOrbiting;
    private final int numMoons;

    public Planet(double mass, double velocity, String name, double orbitalDuration, CelestialBody bodyOrbiting, int numMoons) {
        super(mass, velocity, name);
        this.orbitalDuration = orbitalDuration;
        this.bodyOrbiting = bodyOrbiting;
        this.numMoons = numMoons;
    }

    public double getOrbitalDuration() {
        return orbitalDuration;
    }

    public CelestialBody getBodyOrbiting() {
        return bodyOrbiting;
    }

    public int getNumMoons() {
        return numMoons;
    }

    @Override
    public double duration() {
        return 0;
    }

    @Override
    public CelestialBody orbiting() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;

        Planet p = (Planet) obj;
        return Double.compare(p.orbitalDuration, orbitalDuration) == 0 &&
                numMoons == p.numMoons &&
                Objects.equals(bodyOrbiting, p.bodyOrbiting);
    }
}

class CompareCelestialBody implements Comparator<CelestialBody>{

    @Override
    public int compare(CelestialBody o1, CelestialBody o2) {
        return Double.compare(o2.mass(), o1.mass());
    }
}