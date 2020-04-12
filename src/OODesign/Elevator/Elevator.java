package OODesign.Elevator;

import OODesign.Elevator.exception.OverWeightException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {
    private List<ElevatorButton> elevatorButtons = new ArrayList<>();
    private PriorityQueue<Integer> upStops = new PriorityQueue<>();
    private PriorityQueue<Integer> downStops = new PriorityQueue<>((i1,i2) -> {
        return i2 - i1;
    });
    private int currentLevel = 1;
    private Status status = Status.Idle;
    private boolean gateOpen = false;
    private float weightLimit = 1000;
    private float weight = 0;

    public Elevator(){
        for (int i = 1 ; i <= 20 ; i ++){
            ElevatorButton button = new ElevatorButton();
            button.setLevel(i);
            button.setElevator(this);
            this.elevatorButtons.add(button);
        }
    }

    public List<ElevatorButton> getElevatorButtons() {
        return elevatorButtons;
    }

    public void setElevatorButtons(List<ElevatorButton> elevatorButtons) {
        this.elevatorButtons = elevatorButtons;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isGateOpen() {
        return gateOpen;
    }

    public void setGateOpen(boolean gateOpen) {
        this.gateOpen = gateOpen;
    }

    public float getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(float weightLimit) {
        this.weightLimit = weightLimit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void handleExternalRequest(ExternalRequest request) throws OverWeightException{
        Direction d = request.getD();
        if (d.equals(Direction.Down))
            this.status = Status.Down;
        else if(d.equals(Direction.Up))
            this.status = Status.Up;

        if (getCurrentWeight() > weightLimit){
            throw new OverWeightException();
        }

        closeGate();
        this.currentLevel = request.getValue();
        openGate();

        customerComein(100);
        pressButton(request);
        handleInternalRequest();
    }

    private void customerComein(Integer weight){
        this.setWeight(this.getWeight() + weight);
    }

    private void pressButton(ExternalRequest request){
        int targetlevel = request.getLevel();
        for (ElevatorButton b :
                elevatorButtons) {
            if (b.getLevel() == targetlevel){
                b.setPressed(true);
                break;
            }
        }

    }

    public void handleInternalRequest(){
        for (ElevatorButton b : elevatorButtons) {
            if (b.isPressed()){
                int targetlevel = b.getLevel();
                if (targetlevel > this.currentLevel && this.status.equals(Status.Up)){
                    upStops.add(targetlevel);
                } else if (targetlevel < this.currentLevel && this.status.equals(Status.Down)){
                    downStops.add(targetlevel);
                }
            }
        }

        if (status.equals(Status.Up)){
            for (Integer floor: upStops) {
                currentLevel = floor;
                openGate();
                closeGate();
            }
        }else if(status.equals(Status.Down)){
            for (Integer floor: downStops) {
                currentLevel = floor;
                openGate();
                closeGate();
            }
        }

        status = Status.Idle;
    }

    public void openGate(){
        gateOpen = true;
    }

    public void closeGate(){
        gateOpen = false;
    }

    public float getCurrentWeight() {
        return weight;
    }

    public boolean isRequestValid(InternalRequest request){
        return true;
    }
}
