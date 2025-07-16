package lld_problems.elevatorsystem;
import java.util.ArrayList;
import java.util.List;
public class ElevatorController {
    private final List<Elevator> elevators = new ArrayList<>();
    private ElevatorAssignmentStrategy assignmentStrategy;

    public ElevatorController(int numElevators, int capacity, RequestProcessingStrategy processingStrategy, 
                              ElevatorAssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
        for (int i = 0; i < numElevators; i++) {
            Elevator elevator = new Elevator(i, capacity, processingStrategy);
            elevators.add(elevator);
            new Thread(elevator).start();
        }
    }

    public void handleRequest(Request request) {
        Elevator selected = assignmentStrategy.assignElevator(elevators, request);
        if (selected != null) {
            selected.addRequest(request);
        } else {
            System.out.println("No elevator available for request from floor " + request.getSourceFloor());
        }
    }

    public void setAssignmentStrategy(ElevatorAssignmentStrategy strategy) {
        this.assignmentStrategy = strategy;
    }
}

