package Concurrent.Character8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTask {
    public static void main(String[] args) {

    }

    //每周四 18：00 执行定时任务
    public static void method3() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime time = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        System.out.println(time);

        // initailDeal
        long period = 1000 * 60 * 60 * 24 * 7;

        if(now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }
        System.out.println(time);

        long intitialDelay = Duration.between(now, time).toMillis();

        pool.scheduleWithFixedDelay(() -> {

        }, intitialDelay, period , TimeUnit.MICROSECONDS);
    }

    public static void method2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleAtFixedRate(() -> {
            System.out.println("running...");
        }, 1, 1, TimeUnit.SECONDS);

        pool.scheduleWithFixedDelay(() -> {
            System.out.println("running...");
        }, 1, 1, TimeUnit.SECONDS);
    }

    public static void method1() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        pool.schedule(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1");
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            System.out.println("task2");
        }, 1, TimeUnit.SECONDS);
    }
}
