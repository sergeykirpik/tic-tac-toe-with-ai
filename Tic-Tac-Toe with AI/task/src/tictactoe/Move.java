package tictactoe;

public class Move {

    private final int row;
    private final int col;
    private final int score;

    public Move(int row, int col, int score) {
        this.row = row;
        this.col = col;
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
