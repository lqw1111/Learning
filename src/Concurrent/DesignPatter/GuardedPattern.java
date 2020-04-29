package Concurrent.DesignPatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

//保护性暂停
public class GuardedPattern {

    //线程1等待 线程2 的下载结果
    public static void main(String[] args) throws InterruptedException {

        for (int i  = 0 ; i < 3 ; i ++){
            new People().start();
        }

        Thread.sleep(1000);

        for (Integer id : Mailboxes.getIds()) {
            new Postman(id, "内容" + id).start();
        }
    }
}

class Downloader {
    public static List<String> download() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.google.ca").openConnection();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}

class People extends Thread {
    @Override
    public void run() {
        //收信
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        System.out.println("收信");
        Object mail = guardedObject.get(5000);
        System.out.println("收到信 " + mail);
    }
}

class Postman extends Thread {

    private int id;

    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guarded = Mailboxes.getGuarded(id);
        System.out.println("送信" + id + " " + mail);
        guarded.complete(mail);
    }
}

class Mailboxes {

    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();

    private static int id = 1;

    //产生唯一id
    private static synchronized int generateId() {
        return id ++;
    }

    public static GuardedObject getGuarded(int id) {
        return boxes.remove(id);
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class GuardedObject{

    private int id;

    private Object response;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //获取结果
    //timeout表示最多等多久
    public Object get(long timeout) {
        synchronized (this) {
            //开始时间
            long begin = System.currentTimeMillis();

            //经历的时间
            long passedTime = 0;

            while (response == null){

                //这一轮循环应该等待的时间
                long waitTime = timeout - passedTime;

                //经历的时间超过了最大等待时间，退出循环
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime); //虚假唤醒, 避免虚假唤醒导致时间过长
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    //产生结果
    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }

}