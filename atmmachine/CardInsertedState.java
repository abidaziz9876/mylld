// public class CardInsertedState implements ATMMachineState {
//     private ATMMachine atm;

//     public CardInsertedState(ATMMachine atm) {
//         this.atm = atm;
//     }

//     @Override
//     public void handle() {
//         System.out.println("Card inserted. Please enter your PIN.");
//         int enteredPIN = 1234; // Simulated

//         if (enteredPIN == atm.getCard().getPIN()) {
//             System.out.println("PIN is correct.");
//             atm.setState(new AmountEntryState(atm));
//         } else {
//             System.out.println("Incorrect PIN. Ejecting card.");
//             atm.setState(new IdleState(atm));
//         }
//         atm.process(); // Move to next step
//     }
// }
