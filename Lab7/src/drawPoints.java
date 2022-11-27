import processing.core.PApplet;

public class drawPoints extends PApplet {

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        background(180);
        noLoop();
    }

    public void draw() {

        String[] lines = loadStrings("drawMe.txt");
        println("There are " + lines.length + "lines");

        printLines(lines);
    }

    public static void run() {
        PApplet.main("drawPoints");
    }

    private void printLines(String[] lines) {
        double x, y;

        for (String line : lines) {
            if (line.length() > 0) {
                String[] words = line.split(",");

                print("\nxyz: ");
                for (String word : words){
                    print(word + " ");
                }

                x = Double.parseDouble(words[0]);
                y = Double.parseDouble(words[1]);
                ellipse((int) x, (int) y, 1, 1);
            }
        }
    }
}
