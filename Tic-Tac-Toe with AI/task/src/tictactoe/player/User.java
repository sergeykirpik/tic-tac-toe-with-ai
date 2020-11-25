package tictactoe.player;

import tictactoe.Game;

import java.util.Scanner;

public class User extends AbstractPlayer {

    private static final Scanner scanner = new Scanner(System.in);

    public User(Game game, char playerSymbol) {
        super(game, playerSymbol);
    }

    @Override
    public void makeMove() {
        int[] coordinates = readCoordinates();
        int row = coordinates[0];
        int col = coordinates[1];
        game.makeMove(row, col);
    }

    private int[] readCoordinates() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String[] coordinates = scanner.nextLine().split("\\s+");
            if (coordinates.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            boolean valid = true;
            for (String coordinate : coordinates) {
                if (!coordinate.matches("\\d")) {
                    System.out.println("You should enter numbers!");
                    valid = false;
                    break;
                }
                if (!coordinate.matches("[1-3]")) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                continue;
            }
            int userCol = Integer.parseInt(coordinates[0]);
            int userRow = Integer.parseInt(coordinates[1]);
            int col = toFieldCol(userCol);
            int row = toFieldRow(userRow);
            if (!game.isCellEmpty(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            return new int[]{ row, col };
        }
    }

    private int toFieldCol(int userCol) {
        return userCol - 1;
    }

    private int toFieldRow(int userRow) {
        return game.field.rows() - userRow;
    }
}
