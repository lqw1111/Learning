package java8.Future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction1 {

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);
        }).start();

        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);

        completableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });
    }

    public static double get() {
        try {
            Thread.sleep(random.nextInt(10000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        double result = random.nextDouble();
        System.out.println(result);
        return result;
    }
}
