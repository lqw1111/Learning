package Concurrent.Character8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        // 1、创建semaphore对象
        Semaphore semaphore = new Semaphore(3);

        // 2、创建线程使用
        for (int i = 0 ; i < 10 ; i ++){
            new Thread(() -> {
                // 3、获得许可
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " start");

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{
                    System.out.println(Thread.currentThread().getName() + " end");
                } finally {
                    semaphore.release();
                }
            }, i+"").start();
        }
    }
}
