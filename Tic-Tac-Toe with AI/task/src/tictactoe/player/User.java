package tictactoe.player;

import tictactoe.Game;

import java.util.Scanner;

public class User extends AbstractPlayer {

    private static final Scanner scanner = new Scanner(System.in);

    public User(Game game) {
        super(game);
    }

    @Override
    public void makeMove() {
        int[] coordinates = readCoordinates();
        int userCol = coordinates[0];
        int userRow = coordinates[1];
        game.makeMove(userCol, userRow);
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
            if (!game.isCellEmpty(userCol, userRow)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            return new int[]{ userCol, userRow };
        }
    }
}
