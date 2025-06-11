


/*
1-The vending machine should support multiple products with different prices and quantities.
2-The machine should accept coins and notes of different denominations.
3-The machine should dispense the selected product and return change if necessary.
4-The machine should keep track of the available products and their quantities.
5-The machine should handle multiple transactions concurrently and ensure data consistency.
6-The machine should provide an interface for restocking products and collecting money.
7-The machine should handle exceptional scenarios, such as insufficient funds or out-of-stock products.
*/

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Product coke = vendingMachine.addProduct("Coke", 20, 3);
        Product pepsi = vendingMachine.addProduct("Pepsi", 20, 2);
        // Product water = vendingMachine.addProduct("Water", 10, 5);

        // Select a product
        vendingMachine.selectProduct(coke);

        // Insert coins
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.insertCoin(Coin.FIVE);

        // Insert a note
        vendingMachine.insertNote(Note.HUNDRED);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChange();

        // Select another product
        vendingMachine.selectProduct(pepsi);

        // Insert insufficient payment
        vendingMachine.insertCoin(Coin.FIVE);

        // Try to dispense the product
        vendingMachine.dispenseProduct();

        // Insert more coins
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.TEN);

        // Dispense the product
        vendingMachine.dispenseProduct();

        // Return change
        vendingMachine.returnChange();
    }
}