package lld_problems.tictactoe;


/*
1-The Tic-Tac-Toe game should be played on a 3x3 grid.
2-Two players take turns marking their symbols (X or O) on the grid.
3-The first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.
4-If all the cells on the grid are filled and no player has won, the game ends in a draw.
5-The game should have a user interface to display the grid and allow players to make their moves.
6-The game should handle player turns and validate moves to ensure they are legal.
7-The game should detect and announce the winner or a draw at the end of the game.
*/
class Demo{
    public static void main(String[] args) {
        Player player1=new HumanPlayer("abid", Symbol.X);
        Player player2=new AIPlayer("AI", Symbol.O);
        Player startPlayer=player1;
        TicTacToe ticTacToe=new TicTacToe(3,player1,player2,startPlayer);
        ticTacToe.play();
        System.out.println("game finished");
    }
}