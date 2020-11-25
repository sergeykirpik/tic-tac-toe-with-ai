package tictactoe.player;

import tictactoe.Game;

public abstract class AbstractPlayer implements Player {

    protected final Game game;
    protected final char playerSymbol;

    public AbstractPlayer(Game game, char playerSymbol) {
        this.game = game;
        this.playerSymbol = playerSymbol;
    }
}
