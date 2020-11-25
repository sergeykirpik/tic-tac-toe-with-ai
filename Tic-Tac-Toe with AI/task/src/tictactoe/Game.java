package tictactoe;

import tictactoe.exception.ImpossibleGameFieldStateException;
import tictactoe.exception.InvalidMoveException;

public class Game {
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    public final GameField field = new GameField();

    public void makeMove(int userCol, int userRow) {
        if (!isCellEmpty(userCol, userRow)) {
            throw new InvalidMoveException("Cell is already occupied!");
        }
        int col = toFieldCol(userCol);
        int row = toFieldRow(userRow);
        field.set(row, col, currentPlayer());
    }

    public GameState analyzeMove(int userCol, int userRow, char player) {
        if (!isCellEmpty(userCol, userRow)) {
            throw new InvalidMoveException("Cell is already occupied!");
        }
        int col = toFieldCol(userCol);
        int row = toFieldRow(userRow);

        field.set(row, col, player);
        GameState moveResult = currentState();
        field.set(row, col, CellType.EMPTY);
        return moveResult;
    }

    public boolean isCellEmpty(int userCol, int userRow) {
        int col = toFieldCol(userCol);
        int row = toFieldRow(userRow);
        return field.isEmpty(row, col);
    }

    private int toFieldCol(int userCol) {
        return userCol - 1;
    }

    private int toFieldRow(int userRow) {
        return GameField.SIZE - userRow;
    }

    public char currentPlayer() {
        int countX = field.count(PLAYER_X);
        int countO = field.count(PLAYER_O);
        if (countX == countO) {
            return PLAYER_X;
        }
        if (countX > countO) {
            return PLAYER_O;
        }
        throw new ImpossibleGameFieldStateException();
    }

    public GameState currentState() {
        boolean xWins = checkWinner(PLAYER_X);
        boolean oWins = checkWinner(PLAYER_O);
        if (xWins) {
            return GameState.X_WINS;
        }
        if (oWins) {
            return GameState.O_WINS;
        }
        int countEmpty = field.count(CellType.EMPTY);
        if (countEmpty == 0) {
            return GameState.DRAW;
        }
        return GameState.GAME_NOT_FINISHED;
    }

    private boolean checkWinner(char player) {
        return
            checkRows(player) ||
            checkCols(player) ||
            checkDiagonals(player)
        ;
    }

    private boolean checkRows(char player) {
        boolean check = false;
        for (int row = 0; row < GameField.SIZE; row++) {
            boolean checkRow = true;
            for (int col = 0; col < GameField.SIZE; col++) {
                checkRow &= field.get(row, col) == player;
            }
            check |= checkRow;
        }
        return check;
    }

    private boolean checkCols(char player) {
        boolean check = false;
        for (int col = 0; col < GameField.SIZE; col++) {
            boolean checkCol = true;
            for (int row = 0; row < GameField.SIZE; row++) {
                checkCol &= field.get(row, col) == player;
            }
            check |= checkCol;
        }
        return check;
    }

    private boolean checkDiagonals(char player) {
        boolean check1 = true;
        boolean check2 = true;
        for (int r1 = 0; r1 < GameField.SIZE; r1++) {
            int c2 = GameField.SIZE - r1 - 1;
            check1 &= field.get(r1, r1) == player;
            check2 &= field.get(r1, c2) == player;
        }
        return check1 || check2;
    }

}
