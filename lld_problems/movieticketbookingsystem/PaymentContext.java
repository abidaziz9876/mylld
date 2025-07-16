package lld_problems.movieticketbookingsystem;

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected!");
            return false;
        }
        if(paymentStrategy.pay(amount)){
            return true;
        }
        else{
            return false;
        }
    }
}