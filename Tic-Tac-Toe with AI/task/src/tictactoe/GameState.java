package tictactoe;

public enum GameState {

    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins");

    private final String description;

    GameState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
