package java8.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        List<Integer> integers = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());

        integers.stream().forEach(System.out::println);

        integers.stream().distinct().collect(Collectors.toList());

        integers.stream().skip(5).collect(Collectors.toList());

    }
}
