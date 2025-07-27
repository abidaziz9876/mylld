package lld_problems.atmmachine;

import java.util.Map;

public class DepositState implements ATMMachineState{
    private ATMMachine atm;

    public DepositState(ATMMachine atm) {
        this.atm = atm;
    }
    @Override
    public void handle(Card card) {
        System.out.println("Insert money into the ATM...");
        
        // Simulated deposited notes by user
        Map<Note, Integer> depositedNotes = Map.of(
            Note.FIVE_HUNDRED, 5,  // 5 x 500 = 2500
            Note.TWO_HUNDRED, 2    // 2 x 200 = 400
        );
    
        int depositAmount = atm.calculateTotalAmount(depositedNotes);
    
        // Update user's bank account
        card.getLinkedAccount().deposit(depositAmount);
    
        // Update ATM's internal balance
        atm.addBalance(depositedNotes);
    
        System.out.println(depositAmount + " deposited successfully.");
        atm.setState(new IdleState(atm));
    }
    
}
