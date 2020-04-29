package Concurrent.DesignPatter;

import java.util.LinkedList;

public class ProducerResumer {

    public static void main(String[] args) {
        
        MessageQueue messageQueue = new MessageQueue(2);
        
        for (int i = 0 ; i < 3 ; i ++ ){
            int id = i;
            new Thread(() -> {
                messageQueue.put(new Message(id, "value" + id));
            }, "生产者" + i).start();
        }
        
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = messageQueue.take();
            }
        },"消费者").start();
    }
}

//消息队列，java线程之间通信
class MessageQueue {

    private LinkedList<Message> list = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    //获取消息
    public Message take() {
        //检查队列是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("队列为空，消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("已消费消息");
            Message message = list.removeFirst();
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (list) {
            //检查队列是否已满
            while (list.size() == capacity) {
                try {
                    System.out.println("队列已满，生产者等待");
                    list.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            list.addLast(message);
            System.out.println("生产消息");
            list.notifyAll();
        }
    }
}

final class Message{
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}