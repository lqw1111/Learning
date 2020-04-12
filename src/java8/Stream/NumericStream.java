package java8.Stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStream {

    public static void main(String[] args) {
//        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});
//
//        stream.filter(i -> i > 3);
//
//        Integer result = stream.reduce(0, Integer::sum);

        //使用mapToInt的方式可以节省内存
//        IntStream intStream = stream.mapToInt(i -> i.intValue());

        int a = 9;
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(x -> new int[]{a,x, (int) Math.sqrt(a * a + x * x)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b" + r[1] + ",c=" + r[2]));

    }
}
