package OODesign.ParkingLot;

import org.omg.PortableInterceptor.INACTIVE;

public class Spot {
    private boolean available = true;
    private int level;

    public Spot(int level){
        this.level = level;
    }

    public boolean isAvailable(){
        return available;
    }

    public void takeSpot(){
        available = false;
    }

    public void leaveSpot(){
        available = true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
