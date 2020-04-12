package java8.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {

    private static List<Dish> listDish() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 100, Dish.Type.MEAT),
                new Dish("french fries", false, 200, Dish.Type.OTHER),
                new Dish("pizza", false, 400, Dish.Type.OTHER),
                new Dish("beef", false, 300, Dish.Type.MEAT)
        );
        return menu;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        List<Integer> result = list.stream().map(i -> i * 2).collect(Collectors.toList());

        listDish().stream().map(Dish::getName).forEach(System.out::println);

        String[] words = {"hello","world"};
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));

        stream.flatMap(Arrays::stream).distinct().forEach(System.out::println);

    }
}
