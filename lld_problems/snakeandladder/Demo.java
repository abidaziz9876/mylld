package lld_problems.snakeandladder;

import java.util.Arrays;
import java.util.List;


/*
1-The game should be played on a board with numbered cells, typically with 100 cells.
2-The board should have a predefined set of snakes and ladders, connecting certain cells.
3-The game should support multiple players, each represented by a unique game piece.
4-Players should take turns rolling a dice to determine the number of cells to move forward.
5-If a player lands on a cell with the head of a snake, they should slide down to the cell with the tail of the snake.
6-If a player lands on a cell with the base of a ladder, they should climb up to the cell at the top of the ladder.
7-The game should continue until one of the players reaches the final cell on the board.
8-The game should handle multiple game sessions concurrently, allowing different groups of players to play independently.
*/
class Demo{
    public static void main(String[] args) {
        List<Snake> snakes = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79)
        );

        List<Ladder> ladders = List.of(
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84)
        );

        Board board = new Board(100, snakes, ladders);

        List<String> players = Arrays.asList("Player 1", "Player 2", "Player 3");

        SnakeAndLadderGame game = new SnakeAndLadderGame(board, players, new Dice(1, 6));

        game.play();
    }
}
