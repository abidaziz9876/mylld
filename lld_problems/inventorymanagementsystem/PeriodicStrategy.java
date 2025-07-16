package lld_problems.inventorymanagementsystem;

public class PeriodicStrategy implements ReplenishmentStrategy {
     /*Inventory is reviewed at fixed intervals (e.g., weekly), and replenished based
    on demand forecasts or actual usage.
    
    Example:
    A grocery store checks stock every Monday and reorders based on previous week's sales.
    
    */
    @Override
    public void replenish(Product product) {
        // Let’s say periodic strategy always orders a fixed bulk amount
        int bulkAmount = 100;
        product.setQuantity(product.getQuantity() + bulkAmount);
        System.out.println("Periodic: Replenished " + bulkAmount + " units of " + product.getName());
    }
}

