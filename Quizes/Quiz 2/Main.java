// ------- Main.java ----------

public class Main {
    public static void main(String[] args) {
        GamePiece p = Util.makeCheckerPiece(1, 2);
        Util.moveGamePiece(p, 1, 1);
        System.out.println("located at: " + p.getRow() + ", " + p.getCol());
    }
}