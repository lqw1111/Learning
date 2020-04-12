package java8.Collector;

import java8.Character1.Apple;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green",150),
                new Apple("yellow",120),
                new Apple("green",150),
                new Apple("red",120),
                new Apple("blue",160),
                new Apple("yellow",120)
                );
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String,List<Apple>> groupByCollector(List<Apple> list) {
        return list.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
