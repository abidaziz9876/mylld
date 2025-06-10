public enum Coin {
    ONE(1),FIVE(5),TEN(10);

    private int value;

    Coin(int val){
        this.value=val;
    }
    
    public int getValue(){
        return value;
    }
}
