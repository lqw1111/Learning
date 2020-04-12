package OODesign.Restaurant;

public class Table {
    private boolean isAvailable;
    private int capacity;

    public Table(int capacity){
        this.capacity = capacity;
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
