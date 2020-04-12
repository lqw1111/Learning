package OODesign.Elevator;

import OODesign.Elevator.exception.OverWeightException;

import java.util.List;

public class EvevatorSystem {

    private List<Elevator> elevators;
    private HandleRequestStrategy handleRequestStrategy;

    public void setHandleRequestStrategy(HandleRequestStrategy handleRequestStrategy) {
        this.handleRequestStrategy = handleRequestStrategy;
    }

    public void handleRequest(ExternalRequest request) throws OverWeightException {
        Elevator elevator = this.handleRequestStrategy.handleRequest(request, elevators);
        elevator.handleExternalRequest(request);
    }

}
