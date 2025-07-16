package lld_problems.elevatorsystem;

class Demo{
    public static void main(String[] args) {
        /*
       The elevator system should consist of multiple elevators serving multiple floors.
        Each elevator should have a capacity limit and should not exceed it.
        Users should be able to request an elevator from any floor and select a destination floor.
        The elevator system should efficiently handle user requests and optimize the movement of elevators to minimize waiting time.
        The system should prioritize requests based on the direction of travel and the proximity of the elevators to the requested floor.
        The elevators should be able to handle multiple requests concurrently and process them in an optimal order.
        The system should ensure thread safety and prevent race conditions when multiple threads interact with the elevators.       
        */

        ElevatorAssignmentStrategy assignStrategy = new  NearestElevatorStrategy();
        RequestProcessingStrategy processStrategy = new ScanStrategy(); // Or FCFSStrategy, PriorityStrategy, etc.

        ElevatorController controller = new ElevatorController(3,  5,processStrategy,assignStrategy);

        // Simulate user requests
        controller.handleRequest(new Request(2, 7));
        controller.handleRequest(new Request(4, 1));
        controller.handleRequest(new Request(3, 8));

        controller.handleRequest(new Request(3, 5,Priority.HIGH));
    }
}