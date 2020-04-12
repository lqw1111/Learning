package java8.Future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureInAction3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

//        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
//                .thenApply(CompletableFutureInAction3::multiply)
//                .whenComplete((v, t) -> Optional.ofNullable(v).ifPresent(System.out::println));

        List<Integer> productionsIds = Arrays.asList(1,2,3,4,5);
        Stream<CompletableFuture<Double>> completableFutureStream = productionsIds.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor));
        Stream<CompletableFuture<Double>> completableFutureStream1 = completableFutureStream.map(future -> future.thenApply(CompletableFutureInAction3::multiply));
        List<Double> result = completableFutureStream1.map(CompletableFuture::join).collect(Collectors.toList());

        Optional.ofNullable(result).ifPresent(System.out::println);
    }

    private static double multiply(double value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

    private static double queryProduction(int i) {
        return CompletableFutureInAction1.get();
    }
}
