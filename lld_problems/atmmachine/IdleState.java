package lld_problems.atmmachine;

public class IdleState implements ATMMachineState {
    private ATMMachine atm;

    public IdleState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void handle(Card card) {
        System.out.println("Welcome to XYZ ATM, please insert your card");
        if(card==null){
            System.out.println("please insert your card");
        }
        else{
            atm.setCard(card);
            atm.setState(new CardInsertedState(atm));
            atm.process(card);
        }
    }
}
