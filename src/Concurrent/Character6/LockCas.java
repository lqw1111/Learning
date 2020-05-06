package Concurrent.Character6;

import java.util.concurrent.atomic.AtomicInteger;

public class LockCas {
    // 0 没加锁
    // 1 加锁

    private AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        System.out.println("unlock...");
        state.set(0);
    }

    public static void main(String[] args) {
        LockCas lock = new LockCas();

        new Thread(() -> {
            System.out.println("begin..");
            lock.lock();
            try {
                System.out.println("lock");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            System.out.println("begin...");
            lock.lock();
            try {
                System.out.println("lock...");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
