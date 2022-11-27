import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static processing.core.PApplet.println;

public class Main {
    public static void main(String[] args) {

        // Check input
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected exactly 1 argument: a file name.");
        }

        // Assign file paths
        String filePath = args[0];
        String newFilePath = "drawMe.txt";

        // Create a new file from the given file
        processFile(filePath, newFilePath);

        // Draw the resulting file
        drawPoints.run();
    }

    public static void processFile(String filePath, String newFilePath) {

        // Create a new file
        try (FileWriter newFile = new FileWriter(newFilePath)) {

            // Convert the given file into a stream of points
            Stream<Point> filePointStream = strListToPointStream(Files.readAllLines(new File(filePath).toPath()));

            // Modify the stream of points:
            //  - Remove all points that have a z value > 2.0
            //  - Scale down all the points by 0.5
            //  - Translate all the points by {-150, -37}
            Predicate<Point> zFilter = point -> !(point.z() > 2);
            Function<Point, Point> divide = point -> point.scale(0.5);
            Function<Point, Point> translate = point -> point.translate(-150, -37, 0);

            // Filter the stream and convert to a list of points
            List<Point> filePointList = filePointStream.filter(zFilter).map(divide).map(translate).toList();

            // Write the new list of points back into a file
            for (Point point : filePointList) {
                newFile.write(point.toString());
                newFile.write("\n");
            }

        } catch (IOException e) {
            println("File processing error: " + e);
        }
    }

    public static Stream<Point> strListToPointStream(List<String> stringList) {
        List<Point> pointList = new ArrayList<>();

        for (String line : stringList) {
            pointList.add(Point.parsePoint(line));
        }

        return pointList.stream();
    }
}