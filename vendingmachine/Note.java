public enum Note {
    
    TEN(5),HUNDRED(10),FIVEHUNDRED(500);
    private int value;
    Note(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }
}
