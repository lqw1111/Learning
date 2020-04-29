import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = 0;

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }

}

public class Test1 {

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i = 1 ; i <= n ; i ++){
            if(i % 3 == 0 && i % 5 == 0){
                res.add("FizzBuzz");
            } else if(i % 3 == 0) {
                res.add("Fizz");
            } else if(i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        integer.incrementAndGet();
        Lock lock = new ReentrantLock();
        fizzBuzz(1);
    }
}
