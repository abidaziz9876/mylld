package elevatorsystem;
import java.util.List;

public class NearestElevatorStrategy implements ElevatorAssignmentStrategy {
    @Override
    public Elevator assignElevator(List<Elevator> elevators, Request request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.isFull()) continue;

            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());

            if (distance < minDistance && 
               (elevator.getDirection() == Direction.IDLE || 
               (elevator.getDirection() == Direction.UP && request.getSourceFloor() >= elevator.getCurrentFloor()) ||
               (elevator.getDirection() == Direction.DOWN && request.getSourceFloor() <= elevator.getCurrentFloor()))) {
                
                best = elevator;
                minDistance = distance;
            }
        }

        return best;
    }
}
