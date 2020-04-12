package OODesign.Hotel;

import java.util.Date;

public class Request {
    private Date startDate;
    private Date endDate;

    public Request(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
