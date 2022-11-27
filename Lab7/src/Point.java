import java.util.Arrays;

public record Point(double x, double y, int z) {

    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public boolean equals(Object other) {
        return other instanceof Point &&
                ((Point) other).x == this.x &&
                ((Point) other).y == this.y &&
                ((Point) other).z == this.z;
    }

    public Point translate(double x, double y, int z) {
        return new Point(this.x + x, this.y + y, this.z + z);
    }

    public Point scale(double s) {
        return new Point(this.x * s, this.y * s, (int) (this.z * s));
    }

    public static Point parsePoint(String line) {

        // Convert the string into a set of 3 coordinates
        double[] coords = Arrays.stream(line.split(",")).mapToDouble(Double::parseDouble).toArray();

        // Make sure there's only 3 values
        if (coords.length != 3) {
            throw new NumberFormatException("Expected 3 coordinates, received " + coords.length);
        }

        // Return the new point
        return new Point(coords[0], coords[1], (int) coords[2]);
    }
}
