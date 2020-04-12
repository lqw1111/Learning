package java8.Collector;

import java8.Stream.Dish;

import javax.swing.text.html.Option;
import java.net.CookieHandler;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

public class CollectorsAction2 {

    private static void testGroupingByConcurrent() {
        ConcurrentMap<Dish.Type, List<Dish>> collect = CollectorsAction.menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = CollectorsAction.menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = CollectorsAction.menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoining(){
        System.out.println("testJoining");
        Optional.ofNullable(CollectorsAction.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",")))
                .ifPresent(System.out::println);

        Optional.ofNullable(CollectorsAction.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(",","[","]")))
                .ifPresent(System.out::println);
    }

    private static void testMapping() {
        System.out.println("testMapping");
        Optional.ofNullable(
                CollectorsAction.menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",")))
        ).ifPresent(System.out::println);
    }

    private static void testMaxBy() {
        System.out.println("testMax");

        CollectorsAction.menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) {
//        testGroupingByConcurrent();
//        testGroupingByConcurrentWithFunctionAndCollector();
//        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
    }
}
