package tictactoe.player;

import tictactoe.Game;
import tictactoe.GameState;
import tictactoe.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBot extends AbstractPlayer {
    private static final Random randomGen = new Random();

    public MediumBot(Game game, char playerSymbol) {
        super(game, playerSymbol);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"medium\"");
        List<Move> moveList = new ArrayList<>();
        int maxScore = 0;
        for (int row = 0; row < game.field.rows(); row++) {
            for (int col = 0; col < game.field.cols(); col++) {
                 if (game.isCellEmpty(row, col)) {
                     Move moveX = analyzeMove(row, col, Game.PLAYER_X);
                     Move moveO = analyzeMove(row, col, Game.PLAYER_O);
                     Move moveWithMaxScore = moveX;
                     if (moveO.score() > moveX.score()) {
                         moveWithMaxScore = moveO;
                     }
                     maxScore = Math.max(maxScore, moveWithMaxScore.score());
                     moveList.add(moveWithMaxScore);
                 }
            }
        }
        final int finalMaxScore = maxScore;
        Move[] filteredMoveList = moveList.stream()
            .filter(move -> move.score() == finalMaxScore)
            .toArray(Move[]::new);

        int index = randomGen.nextInt(filteredMoveList.length);
        Move randomMove = filteredMoveList[index];
        game.makeMove(randomMove.row(), randomMove.col());
    }

    private Move analyzeMove(int row, int col, char player) {
        GameState moveResult = game.analyzeMove(row, col, player);
        int score = 0;
        boolean haveWinner =
            moveResult == GameState.X_WINS ||
            moveResult == GameState.O_WINS;

        if (haveWinner) {
            score = 1;
            boolean checkWin = moveResult.winner() == playerSymbol;
            if (checkWin) {
                score = 2;
            }
        }

        return new Move(row, col, score);
    }
}
