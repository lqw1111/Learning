package Concurrent.Character6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);


        i.incrementAndGet(); //等价于 ++i;
        i.getAndIncrement(); // i ++;

        i.getAndAdd(5); // 获取并增加5 打印2，增到7
        i.addAndGet(5); // 打印12， 加到了12

        System.out.println(i.get());

        i.updateAndGet(x -> x * 10); // 原子操作的乘法运算

        updateAndGetTest(i, p-> p * 2);
        i.get();
    }

    public static void updateAndGetTest(AtomicInteger i, IntUnaryOperator operator) {
        while (true) {
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if (i.compareAndSet(prev, next)) {
                break;
            }
        }
    }

}
