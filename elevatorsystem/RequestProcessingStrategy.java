package elevatorsystem;
import java.util.List;

public interface RequestProcessingStrategy {
    Request getNextRequest(List<Request> requests, int currentFloor, Direction direction);
}