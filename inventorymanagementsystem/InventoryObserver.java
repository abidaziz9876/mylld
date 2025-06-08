public interface InventoryObserver {
    void update(Product product);

    //supplier or inventory manager can subscribe for the notification
}