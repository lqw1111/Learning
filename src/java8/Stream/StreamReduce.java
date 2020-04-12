package java8.Stream;

import java.util.Arrays;

public class StreamReduce {

    public static void main(String[] args) {

        //reduce也是一个terminate的操作，聚合操作
        Arrays.stream(new int[]{1,2,3,4,5,6,7,8}).reduce(Integer::sum).ifPresent(System.out::println);
        Arrays.stream(new int[]{1,2,3,4,5,6,7,8}).reduce(Integer::max).ifPresent(System.out::println);
        Arrays.stream(new int[]{1,2,3,4,5,6,7,8}).reduce(Integer::min).ifPresent(System.out::println);
    }
}
