package Future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {
        /*
        AtomicBoolean finished = new AtomicBoolean(false);

        CompletableFuture.supplyAsync(CompletableFutureInAction1::get).whenComplete((v,t) -> {
            Optional.of(v).ifPresent(System.out::println);
            Optional.of(t).ifPresent(x -> x.printStackTrace());
            finished.set(true);
        });
        System.out.println("not block");

        while (!finished.get()){
            Thread.sleep(1);
        }
        */

        Executor executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread();
            t.setDaemon(true);
            return t;
        });
        executor.execute(() -> System.out.println("test"));
    }
}
