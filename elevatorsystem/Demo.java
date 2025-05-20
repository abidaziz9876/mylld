package elevatorsystem;

class Demo{
    public static void main(String[] args) {
        /*
        1-Anyone can make a request to elevator using dial pad 
        2-An Elevator should process the request
        3-handled concurrent request and optimize wait time for the user
        4-multifloor elevator and multiple elevator with a capacity
        5-different strategies for processing of reqeuset by elevator 
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