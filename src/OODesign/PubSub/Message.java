package OODesign.PubSub;

import java.util.Date;

public class Message {
    private Date date;
    private String Content;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
