package lld_problems.fooddeliverysystem;

public class DeliveryAgentObserver implements OrderObserver {
    private final DeliveryAgent agent;

    public DeliveryAgentObserver(DeliveryAgent agent) {
        this.agent = agent;
    }

    @Override
    public void update(Order order) {
        System.out.println("Delivery Agent notified: Assigned to Order " + order.getId());
    }
}

