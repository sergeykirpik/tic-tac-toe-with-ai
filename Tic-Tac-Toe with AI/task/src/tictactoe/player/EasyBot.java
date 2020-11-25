package tictactoe.player;

import tictactoe.Game;
import tictactoe.exception.InvalidGameStateException;

import java.util.Random;

public class EasyBot extends AbstractPlayer {

    private final Random randomGen = new Random();

    public EasyBot(Game game) {
        super(game);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");
        if (game.field.countEmptyCells() == 0) {
            throw new InvalidGameStateException("No more free cells!");
        }
        while (true) {
            int col = randomGen.nextInt(3) + 1;
            int row = randomGen.nextInt(3) + 1;
            if (game.isCellEmpty(col, row)) {
                game.makeMove(col, row);
                break;
            }
        }
    }
}
