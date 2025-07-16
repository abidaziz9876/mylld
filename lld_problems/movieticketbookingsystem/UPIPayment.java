package lld_problems.movieticketbookingsystem;

public class UPIPayment implements PaymentStrategy{
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    

    @Override
    public boolean pay(double amount) {
        System.out.println("Payment successfull of amount "+amount+" with upi id "+upiId);
        return true;
    }
}
