package tictactoe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameField {
    public static int SIZE = 3;

    private String state = emptyState();
    
    public void setState(String state) {
        this.state = state.replace('_', CellType.EMPTY.symbol);
    }
    
    public char get(int row, int col) {
        return state.charAt(index(row, col));
    }

    public void set(int row, int col, char val) {
        int index = index(row, col);
        String newState = state.substring(0, index) + val + state.substring(index + 1);
        setState(newState);
    }

    public void set(int row, int col, CellType cellType) {
        set(row, col, cellType.symbol);
    }

    public void clear() {
        setState(emptyState());
    }

    private String emptyState() {
        return String.valueOf(CellType.EMPTY.symbol).repeat(9);
    }

    public boolean isEmpty(int row, int col) {
        return get(row, col) == CellType.EMPTY.symbol;
    }

    public int count(char cellValue) {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (get(row, col) == cellValue) {
                    count++;
                }
            }
        }
        return count;
    }

    public int count(CellType cellType) {
        return count(cellType.symbol);
    }

    public int countEmptyCells() {
        return count(CellType.EMPTY);
    }

    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        out.println("-".repeat(9));
        for (int row = 0; row < SIZE; row++) {
            out.print("| ");
            for (int col = 0; col < SIZE; col++) {
                out.printf("%c ", get(row, col));
            }
            out.println("|");
        }
        out.println("-".repeat(9));
        return baos.toString();
    }

    private int index(int row, int col) {
        return row * SIZE + col;
    }
}
