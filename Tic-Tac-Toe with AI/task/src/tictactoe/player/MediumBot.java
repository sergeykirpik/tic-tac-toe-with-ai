package tictactoe.player;

import tictactoe.Game;
import tictactoe.GameState;
import tictactoe.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumBot extends AbstractPlayer {
    private static final Random randomGen = new Random();

    public MediumBot(Game game) {
        super(game);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"medium\"");
        List<Move> moveList = new ArrayList<>();
        int maxScore = 0;
        for (int col = 1; col <= 3; col++) {
            for (int row = 1; row <= 3; row++) {
                 if (game.isCellEmpty(col, row)) {
                     Move moveX = analyzeMove(col, row, Game.PLAYER_X);
                     Move moveO = analyzeMove(col, row, Game.PLAYER_O);
                     Move moveWithMaxScore = moveX;
                     if (moveO.score() > moveX.score()) {
                         moveWithMaxScore = moveO;
                     }
                     maxScore = Math.max(maxScore, moveWithMaxScore.score());
                     moveList.add(moveWithMaxScore);
                     moveList.add(new Move(col, row, 0));
                 }
            }
        }
        final int finalMaxScore = maxScore;
        Move[] filteredMoveList = moveList.stream()
            .filter(move -> move.score() == finalMaxScore)
            .toArray(Move[]::new);

        int index = randomGen.nextInt(filteredMoveList.length);
        Move randomMove = filteredMoveList[index];
        game.makeMove(randomMove.col(), randomMove.row());
    }

    private Move analyzeMove(int col, int row, char player) {
        GameState moveResult = game.analyzeMove(col, row, player);
        int score = 0;
        char currentPlayer = game.currentPlayer();
        boolean haveWinner =
            moveResult == GameState.X_WINS ||
            moveResult == GameState.O_WINS;

        if (haveWinner) {
            score = 1;
            boolean checkWin =
                moveResult == GameState.X_WINS &&
                currentPlayer == Game.PLAYER_X;
            checkWin |=
                moveResult == GameState.O_WINS &&
                currentPlayer == Game.PLAYER_O;

            if (checkWin) {
                score = 2;
            }
        }

        return new Move(col, row, score);
    }
}
