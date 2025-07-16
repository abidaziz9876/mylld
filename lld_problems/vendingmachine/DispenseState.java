package lld_problems.vendingmachine;

public class DispenseState implements VendingMachineState {
    @Override
    public void handleRequest(VendingMachine vm) {
        Product product = vm.selectedProduct;
        int quantity = vm.selectedQuantity;
        int currentQty = vm.inventory.getOrDefault(product, 0);
        int totalCost = product.getPrice() * quantity;

        if (currentQty < quantity) {
            System.out.println("Not enough stock to fulfill request.");
            vm.setState(new IdleState());
            return;
        }

        if (vm.balance >= totalCost) {
            vm.updateInventory(product, quantity);
            vm.balance -= totalCost;
            System.out.println("Dispensing: " + product + " x" + quantity);
            if (vm.balance > 0) {
                System.out.println("Returning change: " + vm.balance);
            }
            vm.balance = 0;
            vm.selectedProduct = null;
            vm.selectedQuantity = 0;
            vm.setState(new IdleState());
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

