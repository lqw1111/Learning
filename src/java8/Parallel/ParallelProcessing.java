package java8.Parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelProcessing {

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0 ; i < 10 ; i ++){
            long startTimeStamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duraton = System.currentTimeMillis() - startTimeStamp;
            System.out.println("The result of sum => " + result);
            if (duraton < fastest) fastest = duraton;
        }
        return fastest;
    }

    private static long parallelStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream2(long limit){
        return LongStream.rangeClosed(1,limit).parallel().reduce(0L, Long::sum);
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        System.out.println("the best time " + measureSumPerformance(ParallelProcessing::iterateStream,10000000));
    }
}
