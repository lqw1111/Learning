package OODesign.PubSub;

public class SubscriberA extends Subscriber {

    private MessageChannel messageChannel;

    public SubscriberA(MessageChannel messageChannel) {
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
