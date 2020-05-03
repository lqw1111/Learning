package Concurrent.DesignPatter;


//犹豫模式，用在一个线程发现另一个线程或本线程已经做了某一件相同的事，那么本线程就无需在做了，直接结束返回
public class BalkingPattern {

    private volatile boolean starting;

    public void start() {
        System.out.println("尝试启动监控线程...");

        synchronized (this) {
            if (starting) {
                return;
            }
            starting = true;
        }

        //真正启动监控线程...
    }
}
