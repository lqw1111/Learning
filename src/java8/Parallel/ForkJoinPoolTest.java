package java8.Parallel;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    private static int[] data = {1,2,3,4,5,6,7,8,9,10};

    private static int calc(){
        int res = 0;
        for (int d : data) {
            res += d;
        }
        return res;
    }

    public static void main(String[] args) {
        AccmulatorRecursiveTask task = new AccmulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println(result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println(AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }
}
