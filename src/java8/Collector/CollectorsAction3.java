package java8.Collector;

import java8.Stream.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CollectorsAction3 {

    private static void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        Optional.of(
                CollectorsAction.menu.stream().collect(Collectors.partitioningBy(Dish::isVegatarian)))
                .ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.println("testPartitioningByWithPredicate");
        Optional.of(
                CollectorsAction.menu.stream().collect(Collectors.partitioningBy(Dish::isVegatarian, Collectors.averagingDouble(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperarot");
        CollectorsAction.menu.stream()
                .collect(
                        Collectors.reducing(
                                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))
                        )
                ).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperatorAndIdentity() {
        Integer res = CollectorsAction.menu.stream()
                .map(Dish::getCalories)
                .collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(res);
    }

    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(CollectorsAction.menu.stream()
                .collect(Collectors.summarizingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        testSummarizingDouble();
    }
}
