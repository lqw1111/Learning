package Concurrent.Character8;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

public class ReadWriteLock {
    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            try {
                dataContainer.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            dataContainer.write();
        }, "t2").start();
    }
}

class DataContainer {
    private Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() throws InterruptedException {
        System.out.println("获取读所");
        r.lock();
        try {
            System.out.println("读取");
            sleep(1000);
            return data;
        } finally {
            System.out.println("释放");
            r.unlock();
        }
    }

    public void write() {
        System.out.println("获取写锁");
        w.lock();
        try {
            System.out.println("写入");
        } finally {
            System.out.println("释放写锁");
            w.unlock();
        }
    }
}