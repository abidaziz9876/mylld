package lld_problems.elevatorsystem;
import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable {
    private final int elevatorNumber;
    private int currentFloor;
    private Direction direction;
    private final int capacity;
    private final List<Request> requests;
    private final RequestProcessingStrategy processingStrategy;

    public Elevator(int elevatorNumber, int capacity, RequestProcessingStrategy strategy) {
        this.elevatorNumber = elevatorNumber;
        this.capacity = capacity;
        this.processingStrategy = strategy;
        this.requests = new ArrayList<>();
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public synchronized void addRequest(Request request) {
        requests.add(request);
        System.out.println("Elevator " + elevatorNumber + ": New request added from floor "
                + request.getSourceFloor() + " to " + request.getDestFloor());
        notify(); // wake up if waiting
    }

    public boolean isFull() {
        return requests.size() >= capacity;
    }

    @Override
    public void run() {
        System.out.println("Elevator " + elevatorNumber + " started.");
        while (true) {
            Request nextRequest;
            synchronized (this) {
                while (requests.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                nextRequest = processingStrategy.getNextRequest(new ArrayList<>(requests), currentFloor, direction);
                requests.remove(nextRequest);
                //Avoid ConcurrentModificationException (Safety)
                //outside this control, someone could modify the original requests list.
                //we are protecting the internal data of the Elevator class.
                //Even if the strategy modifies the copy (e.g. sorts it, filters it), it won’t affect our real queue.
            }

            System.out.println("Elevator " + elevatorNumber + ": Processing request from floor "+ nextRequest.getSourceFloor() + " to " + nextRequest.getDestFloor());

            moveTo(nextRequest.getSourceFloor());
            moveTo(nextRequest.getDestFloor());
        }
    }

    private void moveTo(int floor) {
        while (currentFloor != floor) {
            if (currentFloor < floor) {
                direction = Direction.UP;
                currentFloor++;
            } else {
                direction = Direction.DOWN;
                currentFloor--;
            }

            System.out.println("Elevator " + elevatorNumber + " at floor " + currentFloor);
            try {
                Thread.sleep(500); // simulate movement delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        direction = Direction.IDLE;
    }
}
