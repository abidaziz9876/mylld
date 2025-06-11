import java.time.LocalDate;
import java.util.List;


/*
1-The car rental system should allow customers to browse and reserve available cars for specific dates.
2-Each car should have details such as make, model, year, license plate number, and rental price per day.
3-Customers should be able to search for cars based on various criteria, such as car type, price range, and availability.
4-The system should handle reservations, including creating, modifying, and canceling reservations.
5-The system should keep track of the availability of cars and update their status accordingly.
6-The system should handle customer information, including name, contact details, and driver's license information.
7-The system should handle payment processing for reservations.
8-The system should be able to handle concurrent reservations and ensure data consistency.
*/
public class CarRentalSystemDemo {
    public static void main(String[] args) {
        
        CarRentalSystem rentalSystem = CarRentalSystem.getInstance();

        // Add cars to the rental system
        rentalSystem.addCar(new Cars("Toyota", "Camry", 2022, "ABC123", 50.0));
        rentalSystem.addCar(new Cars("Honda", "Civic", 2021, "XYZ789", 45.0));
        rentalSystem.addCar(new Cars("Ford", "Mustang", 2023, "DEF456", 80.0));

        // Create customers
        Customer customer1 = new Customer("John Doe", "john@example.com", "DL1234");

        // Make reservations
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);
        List<Cars> availableCars = rentalSystem.searchCars("Toyota", "Camry", startDate, endDate);
        if (!availableCars.isEmpty()) {
            Cars selectedCar = availableCars.get(0);
            Reservation reservation = rentalSystem.makeReservation(customer1, selectedCar, startDate, endDate);
            if (reservation != null) {
                boolean paymentSuccess = rentalSystem.processPayment(reservation);
                if (paymentSuccess) {
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
                } else {
                    System.out.println("Payment failed. Reservation canceled.");
                    rentalSystem.cancelReservation(reservation.getReservationId());
                }
            } else {
                System.out.println("Selected car is not available for the given dates.");
            }
        } else {
            System.out.println("No available cars found for the given criteria.");
        }
    }
}