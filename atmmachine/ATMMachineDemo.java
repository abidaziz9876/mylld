import java.util.Map;

public class ATMMachineDemo {
    public static void main(String[] args) {
        ATMMachine atm = ATMMachine.getInstance();
        atm.addBalance(Map.of(Note.FIVE_HUNDRED, 10)); // ₹5000
        atm.process();
    }
}
