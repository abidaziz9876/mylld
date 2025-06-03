
import java.util.*;

public class ShoppingCart {
    private final Customer customer;
    private final List<CartItem> items = new ArrayList<>();
    private Coupon appliedCoupon;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void applyCoupon(Coupon coupon) {
        if (coupon.isApplicable(this, customer)) {
            if (appliedCoupon != null) {
                System.out.println("Replacing previous coupon: " + appliedCoupon.getCode());
            }
            appliedCoupon = coupon;
            System.out.println("Coupon " + coupon.getCode() + " applied.");
        } else {
            System.out.println("Coupon " + coupon.getCode() + " is not applicable");
        }
    }
    

    public double calculateTotal() {
        double totalPrice = getTotalPrice();
        double finalPrice=totalPrice;
        if (appliedCoupon != null) {
            CouponManager.getInstance().recordUsage(appliedCoupon.getCode(), customer);
            finalPrice = appliedCoupon.applyDiscount(this, totalPrice);
        }
    
        return finalPrice;
    }

    public double getTotalPrice() {
        double totalPrice = items.stream().mapToDouble(CartItem::getTotalPrice).sum();
        return totalPrice;
    }
    
    public void removeCoupon() {
        if (appliedCoupon != null) {
            System.out.println("Removed coupon: " + appliedCoupon.getCode());
            appliedCoupon = null;
        }
    }
    
    public List<CartItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }
}
