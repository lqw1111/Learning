package java8.Parallel;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorInAction {

    public static void main(String[] args) {
//        IntStream intStream = IntStream.rangeClosed(0, 10);
//        Spliterator.OfInt spliterator = intStream.spliterator();
//        Consumer<Integer> consumer = i -> System.out.println(i);
//        spliterator.forEachRemaining(consumer);

        MySpliteratorText text = new MySpliteratorText("askdflad\nfklasdjfjalsfjlajd\nf");
        Optional.of(text.stream().count()).ifPresent(System.out::println);

        text.stream().forEach(System.out::println);
    }

    static class MySpliteratorText {
        private final String[] data;

        public MySpliteratorText(String text) {
            Objects.requireNonNull(toString(), "can not be null");
            this.data = text.split("\n");
        }

        public Stream<String> stream() {
            return StreamSupport.stream(new MySpliterator(), false);
        }

        public Stream<String> parallelStream() {
            return StreamSupport.stream(new MySpliterator(), true);
        }

        private class MySpliterator implements Spliterator<String> {

            private int start, end;

            public MySpliterator(){
                start = 0;
                end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end){
                this.start = start;
                this.end = end;
            }

            @Override
            //试图从流中拿出元素去消费，有就进行消费，没有就返回false
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start <= end) {
                    action.accept(MySpliteratorText.this.data[start ++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return  null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;
                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE| SIZED| SUBSIZED;
            }
        }
    }
}
