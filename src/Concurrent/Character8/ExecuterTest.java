package Concurrent.Character8;

import sun.jvm.hotspot.runtime.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecuterTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "t1 running");
            Thread.sleep(1000);
            return 1;
        });

        Future<Integer> result2 = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "t2 running");
            Thread.sleep(1000);
            return 2;
        });

        Future<Integer> result3 = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "t3 running");
            Thread.sleep(1000);
            return 3;
        });

        System.out.println("shut down");
        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);
    }
}
