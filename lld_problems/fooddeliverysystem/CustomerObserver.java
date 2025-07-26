package lld_problems.fooddeliverysystem;

public class CustomerObserver implements OrderObserver {
    private final Customer customer;

    public CustomerObserver(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void update(Order order) {
        System.out.println("Customer notified: Order " + order.getId() + " is now " + order.getStatus());
    }
}

