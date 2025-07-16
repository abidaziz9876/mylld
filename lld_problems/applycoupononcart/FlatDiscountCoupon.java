package lld_problems.applycoupononcart;

import java.time.LocalDate;

public class FlatDiscountCoupon extends AbstractCoupon {
    private final double amount;
    private final double minCartValue;
    public FlatDiscountCoupon(String code, double amount, LocalDate expiry, int globalLimit, int userLimit,double minCartValue) {
        super(code, expiry, globalLimit, userLimit);
        this.amount = amount;
        this.minCartValue=minCartValue; 
    }

    public double applyDiscount(ShoppingCart cart, double currentTotal) {
        CouponManager.getInstance().recordUsage(code, cart.getCustomer());
        return Math.max(currentTotal - amount, 0);
    }

    @Override
    public boolean isApplicable(ShoppingCart cart, Customer customer) {
        if (!super.isApplicable(cart, customer)) return false;
        return cart.getTotalPrice() >= minCartValue;
    }

}

