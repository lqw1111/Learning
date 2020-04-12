package OODesign.Hotel;

public class Room {
    private boolean available;
    private RoomType roomType;
    private Integer roomNumber;

    public Room(RoomType roomType, Integer roomNumber) {
        this.available = true;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
}
