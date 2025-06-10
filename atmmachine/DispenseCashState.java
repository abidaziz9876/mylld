public class DispenseCashState implements ATMMachineState {
    private ATMMachine atm;
    private int amount;

    public DispenseCashState(ATMMachine atm, int amount) {
        this.atm = atm;
        this.amount = amount;
    }

    @Override
    public void handle() {
        System.out.println("Dispensing ₹" + amount);
        atm.deductBalance(amount);
        System.out.println("Transaction complete. Returning to idle.");
        atm.setState(new IdleState(atm));
        atm.process();
    }
}
