package OODesign.PubSub;

import java.util.Calendar;
import java.util.Date;

public class PublisherA extends Publisher {

    private String topic;
    private MessageChannel messageChannel;

    public PublisherA(MessageChannel messageChannel, String topic) {
        this.messageChannel = messageChannel;
        this.topic = topic;
    }

    @Override
    public void publish() {
        Message message = new Message();
        message.setContent("content");
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        message.setDate(time);

        messageChannel.publishMsg(this, message);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
