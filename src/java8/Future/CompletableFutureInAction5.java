package java8.Future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureInAction5 {
    public static void main(String[] args) throws InterruptedException {
//        CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
//                    System.out.println(Thread.currentThread().getName());
//                    return 2;
//                }), () -> System.out.println("done"));

        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), v -> v * 10).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 1");
            return CompletableFutureInAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am future 2");
            return CompletableFutureInAction1.get();
        }), System.out::println).thenAccept(System.out::println);

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(Collectors.toList());

        CompletableFuture.allOf(new CompletableFuture[collect.size()])
                .thenRun(() -> System.out.println("done"));

        CompletableFuture.anyOf(new CompletableFuture[collect.size()])
                .thenRun(() -> System.out.println("done"));

        Thread.currentThread().join();
    }
}
