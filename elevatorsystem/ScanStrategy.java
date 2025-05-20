package elevatorsystem;
import java.util.List;

public class ScanStrategy implements RequestProcessingStrategy {
    public Request getNextRequest(List<Request> requests, int currentFloor, Direction direction) {
        Request best = null;
        int bestDistance = Integer.MAX_VALUE;
        for (Request req : requests) {
            if ((direction == Direction.UP && req.getSourceFloor() >= currentFloor) ||
                (direction == Direction.DOWN && req.getSourceFloor() <= currentFloor)) {
                int dist = Math.abs(currentFloor - req.getSourceFloor());
                if (dist < bestDistance) {
                    bestDistance = dist;
                    best = req;
                }
            }
        }
        if (best == null && !requests.isEmpty()) {
            // Fallback if no request in current direction
            return requests.get(0);
        }
        return best;
    }
}
