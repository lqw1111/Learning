package OODesign.PubSub;

public abstract class Subscriber {
    public abstract void update();
    public abstract void subscribe(Publisher publisher);
}
