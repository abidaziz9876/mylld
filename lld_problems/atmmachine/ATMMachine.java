package lld_problems.atmmachine;

import java.util.Map;

public class ATMMachine {
    private static ATMMachine instance;
    private ATMMachineState currentState;
    private Card currentCard;
    private int totalBalance;
    private TransactionType transactionType;

    private ATMMachine() {
        currentState = new IdleState(this);
        totalBalance = 0;
    }

    public synchronized static ATMMachine getInstance() {
        if (instance == null) instance = new ATMMachine();
        return instance;
    }

    public void setTransactionType(TransactionType type){
        this.transactionType=type;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }
    public void setState(ATMMachineState state) {
        this.currentState = state;
    }

    public ATMMachineState getCurrentState(){
        return currentState;
    }

    public void process(Card card) {
        currentState.handle(card);
    }

    public void setCard(Card card) {
        this.currentCard = card;
    }

    public Card getCard() {
        return currentCard;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public int calculateTotalAmount(Map<Note, Integer> notes) {
        int total = 0;
        for (Map.Entry<Note, Integer> entry : notes.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }
    public void addBalance(Map<Note, Integer> notes) {
        int amount=calculateTotalAmount(notes);
        totalBalance += amount;
    }

    public void deductBalance(int amount) {
        totalBalance -= amount;
    }
}
