public class TicTacToe {
    Board board;
    Player player1,player2;
    Player startPlayer;
    public TicTacToe(int size, Player player1,Player player2,Player startPlayer){
        board=new Board(size);
        this.player1=player1;
        this.player2=player2;
        this.startPlayer=startPlayer;
    }

    public void play(){
        while(true){
            System.out.println(startPlayer.getName()+"'s turn");
            Position p = startPlayer.move();
            int row = p.getRow();
            int col = p.getCol();
            System.out.println("row- "+row+" col- "+col);
            if (!board.isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue; 
            }
        
            board.placeSymbol(row, col, startPlayer.getSymbol());
            board.display();
        
            if(board.checkWin(startPlayer.getSymbol())){
                System.out.println("Congrats! " + startPlayer.getName() + " won!");
                break;
            }
        
            if(board.checkDraw()){
                System.out.println("It's a draw!");
                break;
            }
        
            switchPlayer();
        }        
    }
    public void switchPlayer(){
        if(startPlayer == player1){
            startPlayer = player2;
        } else {
            startPlayer = player1;
        }
    }
    
}
