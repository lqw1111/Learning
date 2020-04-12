package OODesign.Elevator;

public class ExternalRequest extends Request{

    private Direction d;
    private Integer level;

    public Direction getD() {
        return d;
    }

    public void setD(Direction d) {
        this.d = d;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
