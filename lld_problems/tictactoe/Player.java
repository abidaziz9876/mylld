package lld_problems.tictactoe;

public interface Player {
    Position move();
    Symbol getSymbol();
    String getName();
}
