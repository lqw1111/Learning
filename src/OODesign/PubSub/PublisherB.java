package OODesign.PubSub;

public class PublisherB extends Publisher {

    private MessageChannel messageChannel;

    public PublisherB(MessageChannel messageChannel, String topic) {
        this.messageChannel = messageChannel;
        this.topic = topic;
    }
    @Override
    public void publish() {

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
