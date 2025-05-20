package elevatorsystem;
import java.util.List;

public class PriorityBasedRequestProcessingStrategy implements RequestProcessingStrategy {

    @Override
    public Request getNextRequest(List<Request> requests, int currentFloor, Direction direction) {
        if (requests == null || requests.isEmpty()) return null;

        Request best = null;

        for (Request req : requests) {
            if (best == null) {
                best = req;
            } else {
                // Compare by priority first
                int currentPriority = req.getPriority().ordinal();
                int bestPriority = best.getPriority().ordinal();

                if (currentPriority > bestPriority) {
                    best = req; // Higher priority request
                } else if (currentPriority == bestPriority) {
                    // If same priority, choose the one closer to current floor
                    int currentDist = Math.abs(req.getSourceFloor() - currentFloor);
                    int bestDist = Math.abs(best.getSourceFloor() - currentFloor);

                    if (currentDist < bestDist) {
                        best = req;
                    }
                }
            }
        }

        return best;
    }
}
