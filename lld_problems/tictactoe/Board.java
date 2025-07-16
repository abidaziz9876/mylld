package lld_problems.tictactoe;
public class Board {
    Symbol grid[][];
    int size;
    int movesCount;
    public Board(int size){
        this.size=size;
        grid=new Symbol[size][size];
        movesCount=0;
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j]=Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(int row, int col){
        if(row<0 || row>=size || col<0 || col>=size || grid[row][col]!=Symbol.EMPTY){
            return false;
        }
        return true;
    }
    
    public boolean checkDraw(){
        if(movesCount==size*size)
        return true;
        return false;
    }

    public boolean checkWin(Symbol symbol){
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != symbol) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }

        // Check columns
        for (int j = 0; j < size; j++) {
            boolean colWin = true;
            for (int i = 0; i < size; i++) {
                if (grid[i][j] != symbol) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }

        // Diagonal
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < size; i++) {
            if (grid[i][i]!= symbol) diag1 = false;
            if (grid[i][size - i - 1] != symbol) diag2 = false;
        }

        return diag1 || diag2;
    }

    public void placeSymbol(int row,int col,Symbol symbol){
        grid[row][col]=symbol;
        this.movesCount++;
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            System.out.print("[ ");
            for (int col = 0; col < size; col++) {
                if(grid[row][col]==Symbol.EMPTY){
                    System.out.print(" ");
                }
                else{
                    System.out.print(grid[row][col] + " ");
                }
            }
            System.out.println("]");
            
        }
        System.out.println();
    }
}
