package lld_problems.vendingmachine;

public class Demo {    
    public static void main(String[] args) {
        VendingMachine vm=VendingMachine.getInstance();
        Product chips=new Product("Chips", 100);
        vm.addProduct(chips, 10);
        vm.selectProduct(chips, 2);
        vm.request();                         // select → ready
        vm.insertNote(Note.FIVEHUNDRED);      // insert ₹500
        vm.request();                         // pay → dispense (₹200 used, ₹300 returned)
        vm.request();                         // goes back to idle
    }
}
