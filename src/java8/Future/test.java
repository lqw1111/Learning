package java8.Future;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Queue<FutureTask> q = new ArrayDeque<>();


        FutureTask futureTask = new MyFuntureTask(() -> { return  1;} , System.currentTimeMillis());
        executor.execute(futureTask);
        System.out.println(futureTask.get());

        executor.shutdown();
    }

}


class MyFuntureTask extends FutureTask{

    private long timeStamp;

    public MyFuntureTask(Callable callable, long timeStamp) {
        super(callable);
        this.timeStamp = timeStamp;
    }

}