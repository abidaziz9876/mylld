import java.time.LocalDate;

public class PercentageCoupon extends AbstractCoupon {
    private final double percent;
    double minCartValue;
    public PercentageCoupon(String code, double percent, LocalDate expiry, int globalLimit, int userLimit,double minCartValue) {
        super(code, expiry, globalLimit, userLimit);
        this.percent = percent;
        this.minCartValue=minCartValue;
    }

    public double applyDiscount(ShoppingCart cart, double currentTotal) {
        CouponManager.getInstance().recordUsage(code, cart.getCustomer());
        return currentTotal * (1 - percent / 100.0);
    }

    @Override
    public boolean isApplicable(ShoppingCart cart, Customer customer) {
        if (!super.isApplicable(cart, customer)) return false;

        return cart.getTotalPrice() >= minCartValue;
    }

}
