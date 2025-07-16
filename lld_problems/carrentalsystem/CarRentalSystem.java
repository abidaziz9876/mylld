package lld_problems.carrentalsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private static CarRentalSystem instance;
    private final Map<String, Cars> cars;
    private final Map<String, Reservation> reservations;
    private final PaymentProcessor paymentProcessor;

    private CarRentalSystem() {
        cars = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new CreditCardPaymentProcessor();
    }

    public static synchronized CarRentalSystem getInstance() {
        if (instance == null) {
            instance = new CarRentalSystem();
        }
        return instance;
    }

    public void addCar(Cars car) {
        cars.put(car.getLicensePlate(), car);
    }

    public void removeCar(String licensePlate) {
        cars.remove(licensePlate);
    }

    public List<Cars> searchCars(String make, String model, LocalDate startDate, LocalDate endDate) {
        List<Cars> availableCars = new ArrayList<>();
        for (Cars car : cars.values()) {
            if (car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model) && car.isAvailable()) {
                if (isCarAvailable(car, startDate, endDate)) {
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }

    private boolean isCarAvailable(Cars car, LocalDate startDate, LocalDate endDate) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getCar().equals(car)) {
                if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized Reservation makeReservation(Customer customer, Cars car, LocalDate startDate, LocalDate endDate) {
        if (isCarAvailable(car, startDate, endDate)) {
            String reservationId = generateReservationId();
            Reservation reservation = new Reservation(reservationId, customer, car, startDate, endDate);
            reservations.put(reservationId, reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;
    }

    public synchronized void cancelReservation(String reservationId) {
        Reservation reservation = reservations.remove(reservationId);
        if (reservation != null) {
            reservation.getCar().setAvailable(true);
        }
    }

    public boolean processPayment(Reservation reservation) {
        paymentProcessor.processPayment(reservation.getTotalPrice());
        return false;
    }

    private String generateReservationId() {
        return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
} 
