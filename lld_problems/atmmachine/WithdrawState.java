package lld_problems.atmmachine;

public class WithdrawState implements ATMMachineState{
    private ATMMachine atm;
    public WithdrawState(ATMMachine atm){
        this.atm=atm;
    }
    @Override
    public void handle(Card card) {
        System.out.print("Enter amount: ");
        int amount = 5000; 
        System.out.println(amount);
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
