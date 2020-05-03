package Concurrent.DesignPatter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Coopernation {

    public static void main(String[] args) throws InterruptedException {
        ParkUnparkTest();
    }

    public static void awaitSignalTest() throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a1", a, b);
        }).start();

        new Thread(() -> {
            awaitSignal.print("b1", b, c);
        }).start();

        new Thread(() -> {
            awaitSignal.print("c1 ---", c, a);
        }).start();

        Thread.sleep(1000);
        awaitSignal.lock();

        try {
            a.signal();
        } finally {
            awaitSignal.unlock();
        }
    }

    public static void waitNotify() {
        WaitNotify waitNotify = new WaitNotify(1, 5);

        new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }).start();

        new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }).start();

        new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }).start();
    }

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void ParkUnparkTest() {
        ParkUnpark parkUnpark = new ParkUnpark(5);

        t1 = new Thread(() -> {
            parkUnpark.print("a3", t2);
        });
        t2 = new Thread(() -> {
            parkUnpark.print("b3", t3);
        });
        t3 = new Thread(() -> {
            parkUnpark.print("c3", t1);
        });

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}

class ParkUnpark {

    public void print(String str, Thread next) {
        for (int i = 0 ; i < loopNumber; i ++) {
            LockSupport.park();
            System.out.println(str);
            LockSupport.unpark(next);
        }
    }

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    private int loopNumber;
}

class AwaitSignal extends ReentrantLock {

    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current, Condition next) {
        for (int i = 0 ; i < loopNumber ; i ++) {
            lock();
            try {
                current.await();
                System.out.println(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }

}


class WaitNotify {

    private int flag;

    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0 ; i < loopNumber ; i ++){
            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}