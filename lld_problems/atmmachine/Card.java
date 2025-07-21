package lld_problems.atmmachine;

public class Card {
    private  int PIN;
    private int cardNumber;
    private int expiry;
    private Account linkedAccount;
    public Card(int cardNumber, int expiry, int PIN, Account account) {
        this.PIN = PIN;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.linkedAccount = account;
    }
    public int getPIN(){
        return this.PIN;
    }

    public int getExpiry(){
        return this.expiry;

    }

    public int getCardNumber(){
        return this.cardNumber;
    }
    public Account getLinkedAccount() { // <-- Getter to access account
        return this.linkedAccount;
    }
}
