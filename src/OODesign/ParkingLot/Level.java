package OODesign.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Row> rows = new ArrayList<>();
    private int availableCount;

    public Level(int rowCount, int spotCount, int level){
        for (int i = 0 ; i < rowCount ; i ++){
            rows.add(new Row(spotCount, level));
        }
        availableCount = getAvailableCount();
    }

    public Integer getAvailableCount(){
        availableCount = 0;
        for (Row row: rows) {
            availableCount += row.getAvailableCount();
        }
        return availableCount;
    }

    public void updateAvailableCount(int diff){
        availableCount = availableCount + diff;
    }

    public Spot findSpot(){
        for (Row row : rows) {
            if (row.getAvailableCount() > 0){
                return row.findSpot();
            }
        }
        return null;
    }
}
