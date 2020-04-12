package java8.Parallel;

import java.util.concurrent.RecursiveTask;

public class AccmulatorRecursiveTask extends RecursiveTask<Integer> {

    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccmulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= LIMIT) {
            int res = 0;
            for (int i = start ; i < end ; i ++){
                res += data[i];
            }
            return res;
        }
        int mid = (start + end) / 2;
        AccmulatorRecursiveTask left = new AccmulatorRecursiveTask(start, mid, data);
        AccmulatorRecursiveTask right = new AccmulatorRecursiveTask(mid, end, data);

        left.fork();
        Integer rightResult = right.compute();
        Integer leftResult = left.join();

        return rightResult + leftResult;
    }
}
