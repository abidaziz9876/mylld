package lld_problems.atmmachine;

public class SelectTransactionTypeState implements ATMMachineState{
    private ATMMachine atm;
    public SelectTransactionTypeState(ATMMachine atm){
        this.atm=atm;
    }
    @Override
    public void handle(Card card) {
        System.out.println("Select transaction type");
        TransactionType type=TransactionType.WITHDRAW_CASH; //simulated
        System.out.println(type);
        switch(type){
            case CHECK_BALANCE:
                System.out.println("your account balance is "+card.getLinkedAccount().getBalance());
                break;
            case WITHDRAW_CASH:
                atm.setState(new WithdrawState(atm));
                atm.setTransactionType(type);
                atm.process(card);
                break;
            case DEPOSIT_CASH:
                atm.setState(new DepositState(atm));
                atm.setTransactionType(type);
                atm.process(card);
                break;
            default:
                System.out.println("please select a valid option");
        }
    }
    
}
