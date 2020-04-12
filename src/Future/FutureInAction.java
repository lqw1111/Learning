package Future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FutureInAction {

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished";
            }catch (InterruptedException e){
                return "Error";
            }
        });
        System.out.println(future.get());
        while (!future.isDone()){
            Thread.sleep(10000);
        }
        System.out.println(future.get());

        block(() -> {
            try {
                Thread.sleep(10000);
                return "finished";
            }catch (InterruptedException e){
                return "error";
            }
        });
    }

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            T t = callable.action();
            result.set(t);
            finished.set(true);
        });
        thread.start();
        Future<T> future = new FutureInAction.Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}
