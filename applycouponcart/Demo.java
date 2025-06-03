
import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        Customer customer = new Customer("u1", "Alice");
        ShoppingCart cart = new ShoppingCart(customer);

        Product laptop = new Product("p1", "Laptop", 1000);
        Product book = new Product("p2", "Book", 200);

        cart.addItem(new CartItem(laptop, 1));
        cart.addItem(new CartItem(book, 2));

        Coupon flat = new FlatDiscountCoupon("SAVE100", 100,
                LocalDate.of(2025, 12, 31), 5, 2,1000);

        Coupon percent = new PercentageCoupon("PERC10", 10,
                LocalDate.of(2025, 12, 31), 3, 1,1000);

        customer.addCoupon(flat);
        customer.addCoupon(percent);
        Coupon selected = customer.getAvailableCoupons().get(0); // chosen from UI
        cart.applyCoupon(selected); 

        double total = cart.calculateTotal();
        System.out.println("Final total after coupons: " + total);
    }
}
