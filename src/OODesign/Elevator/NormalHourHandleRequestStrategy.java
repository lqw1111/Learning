package OODesign.Elevator;

import java.util.Arrays;
import java.util.List;

public class NormalHourHandleRequestStrategy implements HandleRequestStrategy {
    @Override
    public Elevator handleRequest(ExternalRequest request, List<Elevator> elevators) {
        return findEvelator(request, elevators);
    }


    private Elevator findEvelator(ExternalRequest request, List<Elevator> elevators) {
        for (Elevator e : elevators){
            if (e.getStatus().equals(Status.Idle))
                return e;
        }

        return null;
    }
}
