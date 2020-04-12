package OODesign.PubSub;

public class SubscriberC extends Subscriber {

    private MessageChannel messageChannel;

    public SubscriberC(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    @Override
    public void update() {

    }

    @Override
    public void subscribe(Publisher publisher) {

    }
}
