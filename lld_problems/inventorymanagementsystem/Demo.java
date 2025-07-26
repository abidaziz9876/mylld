package lld_problems.inventorymanagementsystem;

class Demo{
    public static void main(String[] args) {
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // Create and add warehouses
        Warehouse warehouse1 = new Warehouse(1,"Warehouse 1","Delhi");
        Warehouse warehouse2 = new Warehouse(2,"Warehouse 2","Mumbai");
        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);
    
        // Create products using ProductFactory
        
        Product laptop = ProductFactory.createProduct(
            ProductCategory.ELECTRONICS, "SKU123", "Laptop", 1000.0, 50, 25);
        Product tShirt = ProductFactory.createProduct(
            ProductCategory.CLOTHING, "SKU456", "T-Shirt", 20.0, 200, 100);
       
    
        // Add products to warehouses
        warehouse1.addProduct(laptop, 15);
        warehouse1.addProduct(tShirt, 20);
    
        // Set replenishment strategy to Just-In-Time
        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());
    
        // Perform inventory check and replenish if needed
        inventoryManager.performInventoryCheck();
    
        // Switch replenishment strategy to Bulk Order
        inventoryManager.setReplenishmentStrategy(new PeriodicStrategy());
    
        // Replenish a specific product if needed
        inventoryManager.checkAndReplenish("SKU123");    
    }
}
