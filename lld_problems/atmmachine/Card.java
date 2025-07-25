package lld_problems.atmmachine;
import java.time.YearMonth;

public class Card {
    private  int PIN;
    private int cardNumber;
    private YearMonth expiry;
    private Account linkedAccount;
    public Card(int cardNumber, YearMonth expiry, int PIN, Account account) {
        this.PIN = PIN;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.linkedAccount = account;
    }
    public int getPIN(){
        return this.PIN;
    }

    public YearMonth getExpiry(){
        return this.expiry;

    }

    public int getCardNumber(){
        return this.cardNumber;
    }
    public Account getLinkedAccount() {
        return this.linkedAccount;
    }
}
