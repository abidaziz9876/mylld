package lld_problems.elevatorsystem;
import java.util.List;

public interface ElevatorAssignmentStrategy {
    Elevator assignElevator(List<Elevator> elevators, Request request);
}
