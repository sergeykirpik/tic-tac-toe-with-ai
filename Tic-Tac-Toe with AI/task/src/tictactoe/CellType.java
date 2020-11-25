package tictactoe;

public enum CellType {
    EMPTY(' '),
    X('X'),
    O('O');

    public final char symbol;

    CellType(char cellSymbol) {
        this.symbol = cellSymbol;
    }
}
