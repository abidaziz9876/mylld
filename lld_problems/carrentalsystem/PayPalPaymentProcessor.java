package lld_problems.carrentalsystem;

public class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Process PayPal payment
        // ...
        return true;
    }
}