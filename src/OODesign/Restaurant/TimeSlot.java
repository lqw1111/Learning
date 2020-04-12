package OODesign.Restaurant;

public class TimeSlot {
    private int startTime;
    private int endTime;

    public TimeSlot(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean cover(TimeSlot timeSlot){
        if(this.startTime <= timeSlot.startTime && this.endTime >= timeSlot.endTime)
            return true;
        else
            return false;
    }
}
