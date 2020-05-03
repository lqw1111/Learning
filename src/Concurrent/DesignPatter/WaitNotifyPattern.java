package Concurrent.DesignPatter;

public class WaitNotifyPattern {

    private static final Object lock = new Object();

    // 表示t2 是否运行过
    private static boolean t2runned = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (! t2runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1");
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2");
                t2runned = true;
                lock.notify();
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
