package tictactoe;

import tictactoe.player.EasyBot;
import tictactoe.player.MediumBot;
import tictactoe.player.Player;
import tictactoe.player.User;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Game game = new Game();

    public static void main(String[] args) {
        Player[] players = new Player[2];
        while (true) {
            String[] command = readCommand();
            if ("start".equalsIgnoreCase(command[0])) {
                players[0] = createPlayer(command[1]);
                players[1] = createPlayer(command[2]);
                playOneGame(players);
            }
            if ("exit".equalsIgnoreCase(command[0])) {
                break;
            }
        }
    }

    private static String[] readCommand() {
        while (true) {
            System.out.print("Input command: ");
            String[] command = scanner.nextLine().split("\\s+");
            if (command.length < 1 || command.length > 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            if (!command[0].toLowerCase().matches("start|exit")) {
                System.out.println("Bad parameters!");
                continue;
            }
            if ("start".equalsIgnoreCase(command[0])) {
                if (command.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                boolean valid = true;
                for (int i = 1; i <= 2; i++) {
                    valid &= command[i].toLowerCase().matches("user|easy|medium");
                }
                if (!valid) {
                    System.out.println("Bad parameters!");
                    continue;
                }
            }
            return command;
        }
    }

    private static Player createPlayer(String playerType) {
        if ("easy".equalsIgnoreCase(playerType)) {
            return new EasyBot(game);
        }
        if ("medium".equalsIgnoreCase(playerType)) {
            return new MediumBot(game);
        }
        if ("user".equalsIgnoreCase(playerType)) {
            return new User(game);
        }
        throw new RuntimeException("Invalid player type: " + playerType);
    }

    private static void playOneGame(Player[] players) {
        game.field.clear();
        int turn = 0;
        drawGameField();
        while (true) {
            Player currentPlayer = players[turn % 2];
            currentPlayer.makeMove();
            drawGameField();
            GameState gameState = game.currentState();
            if (gameState != GameState.GAME_NOT_FINISHED) {
                System.out.println(gameState);
                break;
            }
            turn++;
        }
    }

    private static void drawGameField() {
        System.out.print(game.field);
    }

}
