package lld_problems.fooddeliverysystem;

public class RestaurantObserver implements OrderObserver {
    private final Restaurant restaurant;

    public RestaurantObserver(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void update(Order order) {
        System.out.println("Restaurant notified: Order " + order.getId() + " is now " + order.getStatus());
    }
}

