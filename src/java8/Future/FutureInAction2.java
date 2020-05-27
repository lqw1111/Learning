package java8.Future;

import java.util.concurrent.*;

public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000);
                return "finshed";
            } catch (InterruptedException e) {
                return "error";
            }
        });

        FutureTask futureTask = new FutureTask(() -> {return 1;});


        String value = future.get();
        System.out.println(value);
        executorService.shutdown();
    }
}
