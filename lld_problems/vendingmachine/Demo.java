package lld_problems.vendingmachine;

import java.util.Map;
import java.util.Vector;

public class Demo {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        Product chips = new Product("Chips", 20);
        Product kurkure = new Product("kurkure", 10);

        vm.addProduct(chips, 5);
        vm.addProduct(kurkure, 5);

        // ❌ Invalid selection
        vm.selectedProduct(Map.of(chips, 10, kurkure, 5));
        vm.request();   // Will print unavailable and allow reselection

        // ✅ User adjusts selection
        vm.selectedProduct(Map.of(chips, 3, kurkure, 5)); // Now valid
        vm.request();  // Transitions to ReadyState
        vm.insertNote(Note.FIVEHUNDRED);
        vm.request();  // Dispenses
    }
}

