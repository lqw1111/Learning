package java8.Future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {

//        CompletableFuture.supplyAsync(() -> 1)
//                .thenApply(i -> Integer.sum(i, 10))
//                .whenComplete((v, t) -> System.out.println(v));

        CompletableFuture.supplyAsync(() -> 1)
                .handle((v,t) -> Integer.sum(v, 10))
                .whenComplete((v,t) -> System.out.println(v))
                .thenRun(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i)).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1).thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1,r2) -> r1 + r2)
                .thenAccept(System.out::println);

        Thread.sleep(1000L);

    }
}
