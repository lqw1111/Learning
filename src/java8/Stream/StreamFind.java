package java8.Stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFind {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7});

        Optional<Integer> optionalInteger1 = stream.filter(i -> i % 2 == 0).findAny();
        Optional<Integer> optionalInteger2 = stream.filter(i -> i % 2 == 0).findFirst();

        int result = find(new Integer[]{1,2,3,4,5,6,7}, -1, i -> i > 10);
    }

    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
        for (int i : values) {
            if (predicate.test(i)){
                return i;
            }
        }
        return defaultValue;
    }
}
