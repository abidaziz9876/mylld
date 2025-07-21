package lld_problems.atmmachine;

public class SelectTransactionType implements ATMMachineState{
    private ATMMachine atm;
    public SelectTransactionType(ATMMachine atm){
        this.atm=atm;
    }
    @Override
    public void handle(Card card) {
        TransactionType type=TransactionType.WITHDRAW_CASH; //simulated
        switch(type){
            case CHECK_BALANCE:
                System.out.println("your account balance is "+card.getLinkedAccount().getBalance());
                break;
            case WITHDRAW_CASH:
                atm.setState(new AmountEntryState(atm));
                atm.setTransactionType(type);
                atm.process(card);
                break;
            case DEPOSIT_CASH:
                atm.setState(new AmountEntryState(atm));
                atm.setTransactionType(type);
                atm.process(card);
                break;
            default:
                System.out.println("please select a valid option");
        }
    }
    
}
