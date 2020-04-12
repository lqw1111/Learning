package OODesign.ParkingLot;

import OODesign.ParkingLot.exception.ParkingLotFullException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Level> levels;
    private float hourlyRate;

    public ParkingLot(int lvl, int rowCount, int spotCount, float hourlyRate){
        this.hourlyRate = hourlyRate;
        levels = new ArrayList<>();
        for (int i = 0 ; i < lvl ; i ++){
            levels.add(new Level(rowCount, spotCount, i));
        }
    }

    public int getAvailableCount(){
        int count = 0;
        for (Level l:
             levels) {
            count += l.getAvailableCount();
        }
        return count;
    }

    public Ticket parkVehicle(Vehicle vehicle) throws Exception{
        Spot spot = findSpotsForVehicle(vehicle);
        if (null == spot)
            throw new ParkingLotFullException("Parking lot is full");
        spot.takeSpot();
        Ticket ticket = new Ticket();
        ticket.setSpot(spot);
        ticket.setVehicle(vehicle);
        ticket.setStartTime(System.currentTimeMillis());
        return ticket;
    }

    private Spot findSpotsForVehicle(Vehicle vehicle){
        for (Level level : levels){
            if (level.getAvailableCount() > 0)
                return level.findSpot();
        }
        return null;
    }

    public float clearSpot(Ticket ticket){
        Spot spot = ticket.getSpot();
        float price = calculatePrice(ticket);
        spot.leaveSpot();

        return price;
    }

    private float calculatePrice(Ticket ticket){
        long curTime = System.currentTimeMillis();
        long time = curTime - ticket.getStartTime();
        return hourlyRate * time ;
    }

}
