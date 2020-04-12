package OODesign.Elevator;

import java.util.List;

public interface HandleRequestStrategy {
    Elevator handleRequest(ExternalRequest request, List<Elevator> elevators);
}
