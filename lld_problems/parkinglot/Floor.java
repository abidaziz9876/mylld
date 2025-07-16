package lld_problems.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public Floor(int floorNumber, int numSpots) {
        this.floorNumber = floorNumber;
        parkingSpots = new ArrayList<>(numSpots);
        // Assign spots in ration of 50:40:10 for bikes, cars and trucks
        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numBikes = (int) (numSpots * spotsForBikes);
        int numCars = (int) (numSpots * spotsForCars);

        for (int i = 1; i <= numBikes; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.BIKE));
        }
        for (int i = numBikes + 1; i <= numBikes + numCars; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.CAR));
        }
        for (int i = numBikes + numCars + 1; i <= numSpots; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.TRUCK));
        }
    }

    public synchronized ParkingSpot parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicle.getType()) {
                spot.parkVehicle(vehicle);
                return new ParkingSpot(spot.getSpotNumber(), vehicle.type);
            }
        }
        return null;
    }

    public synchronized boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                spot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Floor " + floorNumber + " Availability:");
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Spot " + spot.getSpotNumber() + ": " + (spot.isAvailable() ? "Available For"  : "Occupied By ")+" "+spot.getVehicleType());
        }
    }
}