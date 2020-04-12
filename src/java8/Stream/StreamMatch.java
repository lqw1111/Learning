package java8.Stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{0,1,2,3,4,5,6,7});
        boolean matched = stream.allMatch(i -> i > 0);
        assert matched : "some element not matched";

        matched = Arrays.stream(new Integer[]{0,1,2,3,4,5,6,7}).anyMatch(i -> i > 0);
        System.out.println(matched);

        matched = Arrays.stream(new Integer[]{0,1,2,3,4,5,6,7}).noneMatch(i -> i > 0);
        System.out.println(matched);
    }
}
