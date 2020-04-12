package OODesign.PubSub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageChannel {
    public Map<String, Publisher> publisherMap = new HashMap<>();
    public Map<Publisher, List<Subscriber>> subsriberMap = new HashMap<>();
    public Map<Publisher, Queue<Message>> messageQueue = new HashMap<>();

    public void registerPublisher(Publisher publisher){
        if (publisherMap.containsKey(publisher.topic))
            throw new RuntimeException("Topic Exist");
        publisherMap.put(publisher.topic, publisher);
        messageQueue.put(publisher, new LinkedBlockingQueue<>());
    }

    public void subscribe(Subscriber subscriber, Publisher publisher){
        if (!subsriberMap.containsKey(publisher))
            throw new RuntimeException("Publisher does not Exist");
        subsriberMap.get(publisher).add(subscriber);
    }

    public void publishMsg(Publisher publisher, Message message){
        if (!publisherMap.containsKey(publisher))
            throw new RuntimeException("Invalid Publisher");
        messageQueue.get(publisher).offer(message);
    }

    public void update(Subscriber subscriber){

    }


}
