package java8.Collector;

import java8.Stream.Dish;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorsAction4 {

    private static void testSummingDouble() {
        Optional.of(CollectorsAction.menu.stream()
                .collect(Collectors.summarizingDouble(Dish::getCalories))).ifPresent(System.out::println);

        Optional.of(CollectorsAction.menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);
    }

    private static void testToCollection() {
        System.out.println("testToCollection");
        Optional.of(CollectorsAction.menu.stream().collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap() {
        Optional.of(CollectorsAction.menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithBinaryOpreator() {
        Optional.of(CollectorsAction.menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, (d1, d2) -> {return d1 + d2;}))).ifPresent(System.out::println);
    }

    private static void testToMap() {
        //线程安全的

        CollectorsAction.menu.stream().collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap));
    }

    public static void main(String[] args) {

    }
}
