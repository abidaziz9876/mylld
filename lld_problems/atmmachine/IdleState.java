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

        YearMonth now = YearMonth.now();
        if (card.getExpiry().isBefore(now)) {
            System.out.println("Card is expired (Expiry: " + card.getExpiry() + "). Please collect your card.");
            atm.setState(new IdleState(atm)); // remain in Idle
            return;
        }

        System.out.println("Card accepted.");
        atm.setCard(card);
        atm.setState(new CardInsertedState(atm));
        atm.process(card);
    }
}

