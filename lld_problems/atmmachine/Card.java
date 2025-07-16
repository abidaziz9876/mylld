package lld_problems.atmmachine;

public class Card {
    private  int PIN;
    private int cardNumber;
    private int Expiry;
    public Card(int cardNumber, int Expiry, int PIN){
        this.PIN=PIN;
        this.cardNumber=cardNumber;
        this.Expiry=Expiry;
    }
    public int getPIN(){
        return this.PIN;
    }

    public int getExpiry(){
        return this.Expiry;

    }

    public int getCardNumber(){
        return this.cardNumber;
    }

}
