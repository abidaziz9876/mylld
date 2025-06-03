
public interface Coupon {
    String getCode();
    boolean isApplicable(ShoppingCart cart, Customer customer);
    double applyDiscount(ShoppingCart cart, double currentTotal);
    boolean isExclusive();
}

