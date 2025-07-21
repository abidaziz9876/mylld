package lld_problems.atmmachine;

public class DispenseCashState implements ATMMachineState {
    private ATMMachine atm;
    private int amount;

    public DispenseCashState(ATMMachine atm, int amount) {
        this.atm = atm;
        this.amount = amount;
    }

    @Override
    public void handle(Card card) {
        System.out.println("Dispensing " + amount+" rupees");
        atm.deductBalance(amount);
        atm.getCard().getLinkedAccount().withdraw(amount);
        System.out.println("Transaction complete. Returning to idle.");
        atm.setState(new IdleState(atm));
        // atm.process();
    }
}
