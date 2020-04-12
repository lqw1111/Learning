package OODesign.Elevator;

public class ElevatorButton {

    private boolean pressed = false;
    private int level;
    private Elevator elevator;

    public InternalRequest generateInternalRequest(){
        InternalRequest internalRequest = new InternalRequest();
        internalRequest.setValue(level);
        return internalRequest;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
