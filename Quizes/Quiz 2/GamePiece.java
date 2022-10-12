// ------GamePiece.java----------

public class GamePiece {
    private String type;
    private int row;
    private int col;

    public GamePiece(String t, int r, int c) {
        type = t;
        row = r;
        col = c;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

}

