package lld_problems.inventorymanagementsystem;

public class JustInTimeStrategy implements ReplenishmentStrategy {
     // inventory is replenished only when needed, often on a per-order basis.
    // Goal is to minimize holding cost.
    //Example:
    // A car manufacturer orders tires from suppliers only when a customer places a car order.
    
    @Override
    public void replenish(Product product) {
        int requiredQuantity = product.getThreshold() - product.getQuantity();
        product.setQuantity(product.getQuantity() + requiredQuantity);
        System.out.println("Just-In-Time: Replenished " + requiredQuantity + " units of " + product.getName());
    }
}
