package lld_problems.vendingmachine;

public class IdleState implements VendingMachineState {
    @Override
    public void handleRequest(VendingMachine vm) {
        switch (vm.validateSelection()) {
            case NO_SELECTION:
                System.out.println("No product selected.");
                return;
            case PRODUCT_UNAVAILABLE:
                System.out.println("Product unavailable. Please adjust your selection.");
                vm.clearSelection();
                vm.setState(new IdleState());  // clean state reset 
                return;
            case OK:
                System.out.println("Please insert money...");
                vm.setState(new ReadyState());
                return;
        }
    }
}

