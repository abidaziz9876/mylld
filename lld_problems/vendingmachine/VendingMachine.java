package lld_problems.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    Map<Product,Integer> inventory;
    
    VendingMachineState currState;
    int balance=0;
    int totalAmount=0;
    Map<Product,Integer> selectedProducts;
    
    public VendingMachine(){
        inventory=new HashMap<>();
        currState=new IdleState();
        selectedProducts=new HashMap<>();
    }
    
    public void updateInventory(Map<Product,Integer> products){
        for(Map.Entry<Product,Integer> entry: products.entrySet()){
            int oldQuantity=inventory.get(entry.getKey());
            int updatedQuantity=oldQuantity-entry.getValue();
            inventory.put(entry.getKey(), updatedQuantity); 
        }
    }

    public void selectedProduct(Map<Product,Integer> products) {
        this.selectedProducts = products;
    
        int amount = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
    
            System.out.println("Selected product: " + product.name + " (x" + qty + ")");
            amount += qty * product.getPrice();
        }
    
        this.totalAmount = amount;
    }
    

    public SelectionStatus validateSelection() {
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            return SelectionStatus.NO_SELECTION;
        }
    
        for (Map.Entry<Product, Integer> entry : selectedProducts.entrySet()) {
            Product product = entry.getKey();
            int requestedQty = entry.getValue();
            int availableQty = inventory.getOrDefault(product, 0);
    
            if (availableQty < requestedQty) {
                System.out.println(product.name + " is not available in required quantity.");
                System.out.println("Remaining quantity of " +product.getName()+ " is " + availableQty);
                return SelectionStatus.PRODUCT_UNAVAILABLE;
            }
        }
    
        return SelectionStatus.OK;
    }
    
    public void setState(VendingMachineState state){
        this.currState=state;
    }

    public void addProduct(Product product, int quantity){
        inventory.put(product, quantity);
    }

    
    public void removeProduct(Product product){
        inventory.remove(product);
    }

    public void insertNote(Note note){
        balance+=note.getValue();
    }

    public int getBalance(){
        return this.balance;
    }

    public void request(){
        currState.handleRequest(this);
    }

    public void clearSelection() {
        this.selectedProducts = null;
        this.totalAmount = 0;
    }
    
    public void showInventory(){
        for(Map.Entry<Product,Integer> entry: inventory.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}

