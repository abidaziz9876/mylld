package lld_problems.atmmachine;

import java.util.Map;

public class DepositWithdrawState implements ATMMachineState {
    private ATMMachine atm;

    public DepositWithdrawState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle(Card card) {
        if (atm.getTransactionType() == TransactionType.DEPOSIT_CASH) {
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
            return;
        }
        
        int amount = 5000; 
        System.out.println("Requested amount: " + amount+" rupees");
        if(amount>card.getLinkedAccount().getBalance()){
            System.out.println("your account does not have sufficient money");
            System.out.println("car ejected returning to idlestate");
            atm.setState(new IdleState(atm));
        }
        else if(amount>atm.getTotalBalance()){
            System.out.println("ATM machine does not have sufficient amount");
            System.out.println("car ejected returning to idlestate");
            atm.setState(new IdleState(atm));
        }
        else {
            atm.setState(new DispenseCashState(atm, amount));
            atm.process(card);
        }
        
    }

}
