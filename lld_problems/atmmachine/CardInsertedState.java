package lld_problems.atmmachine;

public class CardInsertedState implements ATMMachineState {
    private ATMMachine atm;

    public CardInsertedState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle(Card card) {
        System.out.println("Card inserted. Please enter your PIN.");
        int enteredPIN = 1234; // Simulated

        if (enteredPIN == atm.getCard().getPIN()) {
            System.out.println("PIN is correct.");
            atm.setState(new SelectTransactionType(atm));
            atm.process(card);
        } else {
            System.out.println("Incorrect PIN. Ejecting card.");
            System.out.println("returning to Idle State");
            atm.setState(new IdleState(atm));
        }
    }
}
