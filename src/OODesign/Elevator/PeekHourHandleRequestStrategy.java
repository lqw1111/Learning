package OODesign.Elevator;

import java.util.List;

public class PeekHourHandleRequestStrategy implements HandleRequestStrategy {
    @Override
    public Elevator handleRequest(ExternalRequest request, List<Elevator> elevators) {
        return findEvelator(request,elevators);
    }

    private Elevator findEvelator(ExternalRequest request, List<Elevator> elevators) {
        for (Elevator e : elevators){
            if (e.getStatus().equals(Status.Idle) ||
                    (e.getStatus().equals(Status.Up) && e.getCurrentLevel() < request.getValue()) ||
                    (e.getStatus().equals(Status.Down) && e.getCurrentLevel() > request.getValue()))
                return e;
        }
        return null;
    }
}
