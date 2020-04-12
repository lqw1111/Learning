package java8.Stream;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 100, Dish.Type.MEAT),
                new Dish("french fries", false, 200, Dish.Type.OTHER),
                new Dish("pizza", false, 400, Dish.Type.OTHER),
                new Dish("beef", false, 300, Dish.Type.MEAT)
        );

        List<String> dishNameByCollections = getDishNameByCollections(menu);
        System.out.println(dishNameByCollections);

        menu.stream().filter(d -> {
            System.out.println("filtering ->" + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            return d.getName();
        }).limit(3).collect(Collectors.toList());
    }

    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    private static List<String> getDishNameByCollections(List<Dish> menu) {
        List<Dish> lowColaies = new ArrayList<>();
        //filter and get
        for (Dish d: menu) {
            if (d.getCalories() < 400){
                lowColaies.add(d);
            }
        }
        //sort
        Collections.sort(lowColaies, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> disName = new ArrayList<>();
        for (Dish d : lowColaies) {
            disName.add(d.getName());
        }
        return disName;
    }
}
