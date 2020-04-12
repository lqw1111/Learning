package java8.Collector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

public class CustomerCollectorAction {

    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> collector = new ToListCollector();
        String[] arrs = new String[] {"Alex","Hello","World","Stream"};

        Optional.of(Arrays.asList("Alex","Hello","World","Stream").parallelStream().filter(s -> s.length() > 4)
                .collect(collector)).ifPresent(System.out::println);

    }
}
