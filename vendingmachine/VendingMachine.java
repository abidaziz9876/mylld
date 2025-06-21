import java.util.HashMap;
import java.util.Map;


public class VendingMachine {
    Map<Product,Integer> inventory=new HashMap<>();
    private static VendingMachine instance=null;

    VendingMachineState currState;
    int balance=0;
    Product selectedProduct;
    int selectedQuantity=0;
    private VendingMachine(){
        currState=new IdleState();
    }
    public synchronized static VendingMachine getInstance(){
        if(instance==null){
            instance=new VendingMachine();
        }
        return instance;
    }

    public void updateInventory(Product product, int quantity){
        inventory.put(product, inventory.get(product) - quantity);
    }

    public void selectProduct(Product product, int quantity) {
        if (!inventory.containsKey(product) || inventory.get(product) < quantity) {
            System.out.println("Product not available in required quantity.");
            return;
        }
        this.selectedProduct = product;
        this.selectedQuantity = quantity;
        System.out.println("You selected: " + product + " x " + quantity);
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
}
