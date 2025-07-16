package lld_problems.movieticketbookingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
1-The system should allow users to view the list of movies playing in different theaters.
2-Users should be able to select a movie, theater, and show timing to book tickets.
3-The system should display the seating arrangement of the selected show and allow users to choose seats.
4-Users should be able to make payments and confirm their booking.
5-The system should handle concurrent bookings and ensure seat availability is updated in real-time.
6-The system should support different types of seats (e.g., normal, premium) and pricing.
7-The system should allow theater administrators to add, update, and remove movies, shows, and seating arrangements.
8-The system should be scalable to handle a large number of concurrent users and bookings.
*/
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
            bookingSystem.confirmBooking(booking.getId());
        } else {
            System.out.println("Booking failed. Seats not available.");
        }

        bookingSystem.cancelBooking(booking.getId());
        System.out.println("Booking canceled. Booking ID: " + booking.getId());
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


/*
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MovieTicketBookingSystem bookingSystem = MovieTicketBookingSystem.getInstance();

        // Add movie and theater
        Movie movie = new Movie("Interstellar", 150, "Sci-fi");
        Theatre theater = new Theatre("T1", "PVR", "Mumbai", new ArrayList<>());
        bookingSystem.addMovie(movie);
        bookingSystem.addTheater(theater);

        // Create show with 10x10 seats
        Show show = new Show("S1", movie, theater, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(movie.getMovieDuration()), createSeats(10, 10));
        bookingSystem.addShow(show);

        // Selected seats to simulate race condition
        List<Seat> selectedSeats = Arrays.asList(
                show.getSeats().get("1-1"),
                show.getSeats().get("1-2")
        );

        ExecutorService executor = Executors.newFixedThreadPool(5); // 5 threads simulating users
        CountDownLatch latch = new CountDownLatch(1); // start all threads at once
        //This creates a latch that is waiting for a single signal.

        //All 5 worker threads call latch.await() and get blocked.

        //The main thread calls latch.countDown() once, which releases all 5 waiting threads at the same time.



        for (int i = 1; i <= 5; i++) {
            int userId = i;
            executor.submit(() -> {
                try {
                    latch.await(); // wait for signal to start
                    User user = new User(String.valueOf(userId), "user-" + userId);

                    PaymentContext paymentProcessor = new PaymentContext();
                    paymentProcessor.setPaymentStrategy(new UPIPayment("user" + userId + "@oksbi"));

                    Booking booking = bookingSystem.bookTickets(user, show, selectedSeats, paymentProcessor);

                    if (booking != null) {
                        System.out.println("✅ Booking success for " + user.getName() + " - Booking ID: " + booking.getId());
                    } else {
                        System.out.println("❌ Booking failed for " + user.getName() + " - Seats not available or timeout.");
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(100); // small pause before releasing latch
        latch.countDown(); // start all threads at once

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static Map<String, Seat> createSeats(int rows, int columns) {
        Map<String, Seat> seats = new HashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.NORMAL;
                double price = (seatType == SeatType.PREMIUM) ? 150.0 : 100.0;
                seats.put(seatId, new Seat(seatId, row, col, seatType, price, SeatStatus.AVAILABLE));
            }
        }
        return seats;
    }
}

*/