import java.util.ArrayList;
import java.util.List;

public class Customer {
    private  String id;
    private final String name;
    private final List<Coupon> availableCoupons;
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.availableCoupons = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public List<Coupon> getAvailableCoupons() {
        return availableCoupons;
    }
    public void addCoupon(Coupon coupon) {
        availableCoupons.add(coupon);
    }

    public String getId(){
        return this.id;
    }

    public void removeCoupon(Coupon coupon) {
        availableCoupons.remove(coupon);
    }
}
