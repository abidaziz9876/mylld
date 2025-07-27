package lld_problems.atmmachine;

import java.time.YearMonth;

public class AuthState implements ATMMachineState {
    private ATMMachine atm;

    public AuthState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle(Card card) {
        System.out.println("Please enter your PIN.");
        int enteredPIN = 1234; // Simulated
        YearMonth now = YearMonth.now();
        
        if(card.getPIN()!=enteredPIN){
            System.out.println("Incorrect PIN, Please collect your card");
            atm.setState(new IdleState(atm));
            return;
        }
        else if(card.getExpiry().isBefore(now)){
            System.out.println("Card is expired (Expiry: " + card.getExpiry() + "). Please collect your card.");
            atm.setState(new IdleState(atm)); // remain in Idle
            return;
        }else {
            System.out.println("PIN is correct.");
            atm.setState(new SelectTransactionTypeState(atm));
            atm.process(card);
        }
    }
}
