package OODesign.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Spot> spots;
    private int availableCount;

    public Row(int availableCount, int level){
        this.availableCount = availableCount;
        spots = new ArrayList<>();
        for (int i = 0 ; i < availableCount ; i ++){
            spots.add(new Spot(level));
        }
    }

    public Spot findSpot(){
        for (Spot sp : spots) {
            if (sp.isAvailable())
                return sp;
        }
        return null;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    public int getAvailableCount() {
        availableCount = 0;
        for (Spot sp:
             spots) {
            if (sp.isAvailable())
                availableCount ++;
        }
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}
