public class ReadyState implements VendingMachineState {
    @Override
    public void handleRequest(VendingMachine vm) {
        if (vm.getBalance() >= vm.selectedProduct.getPrice()) {
            System.out.println("Sufficient balance received: " + vm.getBalance());
            vm.setState(new DispenseState());
        } else {
            System.out.println("Current balance: " + vm.getBalance() + ". Please insert more coins.");
        }
    }
}
