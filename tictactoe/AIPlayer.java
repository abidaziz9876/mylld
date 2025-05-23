public class AIPlayer implements Player{
    String name;
    Symbol symbol;
    
    public AIPlayer(String name, Symbol symbol){
        this.name=name;
        this.symbol=symbol;
    }

    @Override
    public Position move() {
        int row = (int) (Math.random() * 3);
        int col = (int) (Math.random() * 3);
        return new Position(row, col);
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public String getName(){
        return name;
    }
    
}
