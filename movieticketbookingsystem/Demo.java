import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Demo{
    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem = MovieTicketBookingSystem.getInstance();

        // Add movies
        Movie movie1 = new Movie("Mission Impossible",120,"action movie");
        Movie movie2 = new Movie("Interstellar",110,"sci-fi movie");
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // Add theaters
        Theatre theater1 = new Theatre("T1", "Theater 1", "Location 1", new ArrayList<>());
        Theatre theater2 = new Theatre("T2", "Theater 2", "Location 2", new ArrayList<>());
        bookingSystem.addTheater(theater1);
        bookingSystem.addTheater(theater2);

        // Add shows
        Show show1 = new Show("S1", movie1, theater1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie1.getMovieDuration()), createSeats(10, 10));
        Show show2 = new Show("S2", movie2, theater2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie2.getMovieDuration()), createSeats(8, 8));
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

        // Book tickets
        User user = new User("1", "demouser");
        PaymentContext paymentProcessor = new PaymentContext();
        paymentProcessor.setPaymentStrategy(new UPIPayment("xyz-1@oksbi"));
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));
        Booking booking = bookingSystem.bookTickets(user, show1, selectedSeats,paymentProcessor);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
        } else {
            System.out.println("Booking failed. Seats not available.");
        }
    }

    private static Map<String, Seat> createSeats(int rows, int columns) {
        Map<String, Seat> seats = new HashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.NORMAL;
                double price = (seatType == SeatType.PREMIUM) ? 150.0 : 100.0;
                Seat seat = new Seat(seatId, row, col, seatType, price, SeatStatus.AVAILABLE);
                seats.put(seatId, seat);
            }
        }
        return seats;
    }
}
