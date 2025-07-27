package lld_problems.atmmachine;

import java.time.YearMonth;

public class IdleState implements ATMMachineState {
    private ATMMachine atm;

    public IdleState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle(Card card) {
        System.out.println("Welcome to XYZ ATM, please insert your card");

        if (card == null) {
            System.out.println("No card detected. Please insert a valid card.");
            return;
        }
        else{
            System.out.println("Card inserted.");
            atm.setCard(card);
            atm.setState(new AuthState(atm));
            atm.process(card);
        }
        
    }
}

