package tictactoe;

public enum GameState {

    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins", Game.PLAYER_X),
    O_WINS("O wins", Game.PLAYER_O);

    private final String description;
    private final char winner;

    GameState(String description) {
        this(description, ' ');
    }

    GameState(String description, char winner) {
        this.description = description;
        this.winner = winner;
    }

    public char winner() {
        return winner;
    }

    @Override
    public String toString() {
        return description;
    }
}
