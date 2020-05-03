package Concurrent.Character4;

public class DeadLock {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){

                }
            }
        }).start();

        new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {

                }
            }
        }).start();
    }
}
