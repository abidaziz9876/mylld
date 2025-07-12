import java.util.ArrayList;
import java.util.List;

/*
1-The parking lot should have multiple levels, each level with a certain number of parking spots.
2-The parking lot should support different types of vehicles, such as cars, motorcycles, and trucks.
3-Each parking spot should be able to accommodate a specific type of vehicle.
4-The system should assign a parking spot to a vehicle upon entry and release it when the vehicle exits.
5-The system should track the availability of parking spots and provide real-time information to customers.
6-The system should handle multiple entry and exit points and support concurrent access.
*/
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
            System.out.println("Car 2 parked, ticket-" + ticket2.getTicketId());
            parkingTickets.add(ticket2.getTicketId());

            Ticket ticket3 = parkingLot.parkVehicle(bike);
            System.out.println("Bike 1 parked, ticket-" + ticket3.getTicketId());
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