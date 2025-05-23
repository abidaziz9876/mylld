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