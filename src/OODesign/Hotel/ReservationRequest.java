package OODesign.Hotel;

import java.util.Date;
import java.util.Map;

public class ReservationRequest {
    private Date startDate;
    private Date endDate;
    private Map<RoomType, Integer> roomNeeded;

    public ReservationRequest(Date startDate, Date endDate, Map<RoomType, Integer> roomNeeded) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomNeeded = roomNeeded;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Map<RoomType, Integer> getRoomNeeded() {
        return roomNeeded;
    }
}
