package java8.Stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {

    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "world","stream", "haha");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("hello","world", "gogo");
    }

    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"hello", "world","stream", "haha"});
    }

    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, n -> n + 2).limit(10);
    }

    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }

    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index,"Name -> " + index);
        }
    }

    static class Obj{
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        createStreamFromValues().forEach(System.out::println);
    }

}
