package OODesign.PubSub;

public class SubscriberB extends Subscriber {

    private MessageChannel messageChannel;

    public SubscriberB(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    @Override
    public void update() {

    }

    @Override
    public void subscribe(Publisher publisher) {
        messageChannel.subscribe(this, publisher);
    }
}
