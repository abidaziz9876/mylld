// public class AmountEntryState implements ATMMachineState {
//     private ATMMachine atm;

//     public AmountEntryState(ATMMachine atm) {
//         this.atm = atm;
//     }

//     @Override
//     public void handle() {
//         int amount = 500; // Simulated input
//         System.out.println("Requested amount: ₹" + amount);

//         if (amount <= atm.getTotalBalance()) {
//             atm.setState(new DispenseCashState(atm, amount));
//         } else {
//             System.out.println("Insufficient ATM balance. Cancelling.");
//             atm.setState(new IdleState(atm));
//         }
//         atm.process();
//     }
// }
