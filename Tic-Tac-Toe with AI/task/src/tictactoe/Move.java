package tictactoe;

public class Move {

    private final int col;
    private final int row;
    private final int score;

    public Move(int userCol, int userRow, int score) {
        col = userCol;
        row = userRow;
        this.score = score;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public int score() {
        return score;
    }
}
