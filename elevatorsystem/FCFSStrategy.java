package elevatorsystem;
import java.util.List;

public class FCFSStrategy implements RequestProcessingStrategy {
    public Request getNextRequest(List<Request> requests, int currentFloor, Direction direction) {
        return requests.get(0);
    }
}

