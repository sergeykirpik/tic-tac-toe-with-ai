package tictactoe.player;

import tictactoe.Game;
import tictactoe.GameState;
import tictactoe.Move;
import tictactoe.exception.InvalidGameStateException;

import java.util.*;

public class HardBot extends AbstractPlayer {

    private static final Random randomGen = new Random();

    public HardBot(Game game, char playerSymbol) {
        super(game, playerSymbol);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"hard\"");
        if (game.field.countEmptyCells() == 0) {
            throw new InvalidGameStateException("Can not make move!");
        }
        List<Move> moveList = new ArrayList<>();
        for (int row = 0; row < game.field.rows(); row++) {
            for (int col = 0; col < game.field.cols(); col++) {
                if (!game.isCellEmpty(row, col)) {
                    continue;
                }
                game.makeMove(row, col);
                Move move = new Move(row, col, computeScore(1));
                moveList.add(move);
                game.undoMove(row, col);
            }
        }
        OptionalInt maxScore = moveList.stream()
            .mapToInt(Move::score)
            .max();

        if (maxScore.isEmpty()) {
            throw new InvalidGameStateException("Can not make move!");
        }

        Move[] filtered = moveList.stream()
            .filter(m -> m.score() == maxScore.getAsInt())
            .toArray(Move[]::new);

        int index = randomGen.nextInt(filtered.length);
        Move randomMove = filtered[index];
        game.makeMove(randomMove.row(), randomMove.col());
    }

    private int computeScore(int level) {
        boolean maximize = level % 2 == 0;
        int rows = game.field.rows();
        int cols = game.field.cols();
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;

        char opponentSymbol = playerSymbol == Game.PLAYER_X ? Game.PLAYER_O : Game.PLAYER_X;

        for (int i = 0; i < rows * cols; i++) {
            int row = i / cols;
            int col = i % cols;
            if (!game.isCellEmpty(row, col)) {
               continue;
            }
            game.makeMove(row, col);
            GameState moveResult = game.currentState();
            int score;
            if (moveResult.winner() == playerSymbol) {
                score = 10;
            } else if (moveResult.winner() == opponentSymbol) {
                score = -10;
            } else if (moveResult == GameState.DRAW) {
                score = 5;
            } else {
                score = computeScore(level + 1);
            }
            minScore = Math.min(minScore, score);
            maxScore = Math.max(maxScore, score);
            game.undoMove(row, col);
        }
        return maximize ? maxScore : minScore;
    }
}
