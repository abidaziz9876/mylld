import java.util.Map;

public class ATMMachine {
    private static ATMMachine instance;
    private ATMMachineState currentState;
    private Card currentCard;
    private int totalBalance;

    private ATMMachine() {
        currentState = new IdleState(this);
        totalBalance = 0;
    }

    public static ATMMachine getInstance() {
        if (instance == null) instance = new ATMMachine();
        return instance;
    }

    public void setState(ATMMachineState state) {
        this.currentState = state;
    }

    public void process() {
        currentState.handle();
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

    public void addBalance(Map<Note, Integer> notes) {
        for (Map.Entry<Note, Integer> entry : notes.entrySet()) {
            totalBalance += entry.getKey().getValue() * entry.getValue();
        }
    }

    public void deductBalance(int amount) {
        totalBalance -= amount;
    }
}
