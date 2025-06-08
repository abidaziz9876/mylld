import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<Floor> floors;
    private FeeStrategy feeStrategy;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private ParkingLot() {
        floors = new ArrayList<>();
    }

    

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(Floor floor) {
        floors.add(floor);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            ParkingSpot spot=floor.parkVehicle(vehicle);
            if(spot!=null){
                System.out.println("Vehicle parked successfully.");
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(ticketId, vehicle, spot);
                activeTickets.put(ticketId, ticket);
                return ticket;
            }
        }
        System.out.println("Could not park vehicle.");
        return null;
    }

    public synchronized double unparkVehicle(String ticketId) throws Exception {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) throw new Exception("Invalid ticket");

        ParkingSpot spot = ticket.getSpot();
        spot.unparkVehicle();

        ticket.setExitTimestamp();
        return feeStrategy.calculateFee(ticket);
    }

    public void displayAvailability() {
        for (Floor level : floors) {
            level.displayAvailability();
        }
    }

    public void setFeeStrategy (FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public FeeStrategy getFeeStrategy(){
        return feeStrategy;
    }
}