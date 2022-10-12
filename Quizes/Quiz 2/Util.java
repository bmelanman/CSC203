// ------- Util.java -----------

public class Util {

    public static GamePiece makeCheckerPiece(int r, int c) {
        return new GamePiece("checkers", r, c);
    }

    public static void moveGamePiece(GamePiece g, int x, int y) {
        g.setRow(y);
        g.setCol(x);
    }
}