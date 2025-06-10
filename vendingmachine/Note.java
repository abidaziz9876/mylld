

public enum Note {
    HUNDRED(100),TWOHUNDRED(200),FIVEHUNDRED(500);

    private int value;
    Note(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
