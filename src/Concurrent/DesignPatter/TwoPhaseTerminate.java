package Concurrent.DesignPatter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TwoPhaseTerminate {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTerminate = new TwoPhaseTermination();
        twoPhaseTerminate.start();

        Thread.sleep(5000);

        twoPhaseTerminate.stop();
    }
}

class TwoPhaseTermination {
    private Thread monitor;

    Map<String,String> map = new HashMap<>();

    //启动监控线程
    public void start() {
        monitor = new Thread(() ->   {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    //重新设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    //停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
