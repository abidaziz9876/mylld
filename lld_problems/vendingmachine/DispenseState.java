package lld_problems.vendingmachine;

public class DispenseState implements VendingMachineState {
    @Override
    public void handleRequest(VendingMachine vm) {
        
        System.out.println("Collect your products and change if any");

        if(vm.balance >= vm.totalAmount){
            int change = vm.balance - vm.totalAmount;
            if (change > 0) {
                System.out.println("Returning change: " + change);
            }
            vm.balance = 0;
        } else {
            System.out.println("Insufficient balance.");
            return;
        }

        vm.updateInventory(vm.selectedProducts);
        System.out.println();
        vm.setState(new IdleState());
    }
}
