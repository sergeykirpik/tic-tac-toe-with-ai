package tictactoe.player;

import tictactoe.Game;
import tictactoe.exception.InvalidGameStateException;

import java.util.Random;

public class EasyBot extends AbstractPlayer {

    private final Random randomGen = new Random();

    public EasyBot(Game game, char playerSymbol) {
        super(game, playerSymbol);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");
        if (game.field.countEmptyCells() == 0) {
            throw new InvalidGameStateException("No more free cells!");
        }
        while (true) {
            int row = randomGen.nextInt(game.field.rows());
            int col = randomGen.nextInt(game.field.cols());
            if (game.isCellEmpty(row, col)) {
                game.makeMove(row, col);
                break;
            }
        }
    }
}
