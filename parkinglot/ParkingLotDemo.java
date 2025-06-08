import java.util.ArrayList;
import java.util.List;

public class ParkingLotDemo {
    public static void main(String[] args) {
    
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Floor(1, 100));
        parkingLot.addLevel(new Floor(2, 80));

        // Vehicle car = new Car("ABC123");
        Vehicle truck = new Truck("XYZ789");
        Vehicle bike = new Bike("M1234");
        List<String> parkingTickets = new ArrayList<>();
        parkingLot.setFeeStrategy(new FlatRateFeeStrategy());
        // Park vehicles
        try {
            
            Ticket ticket2 = parkingLot.parkVehicle(truck);
            System.out.println("Car 2 parked: " + ticket2.getTicketId());
            parkingTickets.add(ticket2.getTicketId());

            Ticket ticket3 = parkingLot.parkVehicle(bike);
            System.out.println("Bike 1 parked: " + ticket3.getTicketId());
            parkingTickets.add(ticket3.getTicketId());

            // Try parking another car when spots are full
            Vehicle car3 = new Car("DL0432");
            parkingLot.parkVehicle(car3);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }


        try {
            double fee = parkingLot.unparkVehicle(parkingTickets.get(0)); // valid ticket ID
            System.out.println("Ticket: " + parkingTickets.get(0) + ", Fee: " + fee);

            fee = parkingLot.unparkVehicle("1"); // invalid ticket ID
        } catch (Exception e) {
            System.out.println("Exception while unparking: " + e.getMessage());
        }

        // Display updated availability
        parkingLot.displayAvailability();
    }
}