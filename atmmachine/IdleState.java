public class IdleState implements ATMMachineState {
    private ATMMachine atm;

    public IdleState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle() {
        System.out.println("ATM is idle. Please insert your card.");
        Card card = new Card(123456, 1234, 1001); // Simulated
        atm.setCard(card);
        atm.setState(new CardInsertedState(atm));
        atm.process(); // Trigger next state
    }
}
