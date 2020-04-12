package java8.Character1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaUsage {

    private static List<Apple> filter(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    private static Apple testBiFunction(String s, Integer weight, BiFunction<String, Integer, Apple> biFunction){
        return biFunction.apply(s, weight);
    }

    private static String testFunction(Apple apple, Function<Apple, String> fun) {
        return fun.apply(apple);
    }

    @FunctionalInterface
    public interface Adder{
        int add(int a, int b);
    }

    public interface SmartAdder extends  Adder {
        int add(long a, long b);
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 120), new Apple("yellow", 170));

        for (Apple apple : filter(apples, apple -> apple.getColor().equals("green"))) {
            System.out.println(apple);
        }

        testFunction(new Apple("yellow", 100), apple -> apple.toString());

        IntFunction<Double> f = i -> i * 100.0d;
        double res = f.apply(10);
        System.out.println(res);

        Apple a = testBiFunction("Blue", 130, (s,w) -> new Apple(s, w));

        Supplier<String> s = String::new;
        System.out.println(s.get().getClass());
    }
}
