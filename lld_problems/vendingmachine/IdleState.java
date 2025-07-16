package lld_problems.vendingmachine;

public class IdleState implements VendingMachineState {
    @Override
    public void handleRequest(VendingMachine vm) {
        if (vm.selectedProduct == null) {
            System.out.println("Please select a product first.");
            return;
        }
        System.out.println("Product selected: " + vm.selectedProduct);
        System.out.println("Please insert money...");
        vm.setState(new ReadyState());
    }
}
