package lld_problems.tictactoe;

import java.util.Scanner;

public class HumanPlayer implements Player{
    String name;
    Symbol symbol;
    public HumanPlayer(String name,Symbol symbol){
        this.name=name;
        this.symbol=symbol;
    }
    @Override
    public Position move() {
        Scanner sc=new Scanner(System.in);
        int row=sc.nextInt();
        int col=sc.nextInt();
        Position p=new Position(row, col);
        return p;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public String getName(){
        return name;
    }
    
}
