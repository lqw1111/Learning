package Concurrent.Character6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AtomicLongTest {
    public static void main(String[] args) throws InterruptedException {
        demo(
                () -> new AtomicLong(0),
                AtomicLong::getAndIncrement
        );

        demo(
                LongAdder::new,
                LongAdder::increment
        );
        demo1(AtomicInteger::incrementAndGet);
    }

    private static <T> void demo (Supplier<T> adderSupplier, Consumer<T> action) throws InterruptedException {
        T adder = adderSupplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i ++){
            ts.add(new Thread(() -> {
                for (int j = 0 ; j < 500000 ; j ++){
                    action.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        Thread.sleep(1000);
        long end = System.nanoTime();
        System.out.println(adder + " cost : " + (end - start) / 1000_000);
    }

    private static void demo1 (Consumer<AtomicInteger> action){
        AtomicInteger adder = new AtomicInteger(0);
        List<Thread> ts = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i ++){
            ts.add(new Thread(() -> {
                for (int j = 0 ; j < 500000 ; j ++){
                    action.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println(adder + " cost : " + (end - start) / 1000_000);
    }
}
