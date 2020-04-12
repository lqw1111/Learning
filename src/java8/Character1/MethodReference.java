package java8.Character1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        useConsumer(consumer, "hello Alex");

        useConsumer(System.out::println, "hello");

        List<Apple> list = Arrays.asList(new Apple("green",110), new Apple("yellow", 150));
        list.sort((a1, a2) -> {
            return (int) (a1.getWeight() - a2.getWeight());
        });

        list.sort(Comparator.comparing(Apple::getWeight));

        Function<String, Integer> f = Integer::parseInt;
        Integer result = f.apply("123");
        System.out.println(result);

        //如果使用类的成员变量则需要包括入参和出参，
        BiFunction<String,Integer, Character> f2 = String::charAt;
        Character c = f2.apply("hello", 2);
        System.out.println(c);

        String s = new String("hello");
        Function<Integer,Character> f3 = s::charAt;
        Character c2 = f3.apply(4);

        Supplier<String> supplier = String::new;
        String s1 = supplier.get();

        BiFunction<String, Long, Apple> appleBiFunction = Apple::new;
        Apple apple = appleBiFunction.apply("yellow", 100l);

        Supplier<String> getColor = apple::getColor;

        a(Apple::getWeight);
    }

    public static Long a(Function<Apple,Long> function){
        Apple apple = new Apple("yello",123);
        return function.apply(apple);
    }


    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }

}
