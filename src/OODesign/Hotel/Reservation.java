package OODesign.Hotel;

import java.util.Date;
import java.util.List;

public class Reservation {
    private List<Room> rooms;
    private Date startDate;
    private Date endDate;

    public Reservation(List<Room> rooms, Date startDate, Date endDate) {
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
