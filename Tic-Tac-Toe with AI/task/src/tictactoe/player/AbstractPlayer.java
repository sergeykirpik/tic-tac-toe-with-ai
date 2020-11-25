package tictactoe.player;

import tictactoe.Game;

public abstract class AbstractPlayer implements Player {

    protected final Game game;

    public AbstractPlayer(Game game) {
        this.game = game;
    }
}
