package Concurrent.Character6;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableTest {
    public static void main(String[] args) throws InterruptedException {
        GarbageBag bag = new GarbageBag("装满了垃圾");

        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<>(bag, true);

        System.out.println("start");

        GarbageBag prev = ref.getReference();

        System.out.println(prev.toString());

        Thread.sleep(1000);

        System.out.println("新欢一只垃圾袋");
        boolean success = ref.compareAndSet(prev, new GarbageBag("空垃圾袋"), true, false);

        System.out.println("换了吗" + success);
        System.out.println(ref.getReference().toString());
    }
}


class GarbageBag{
    String desc;

    public GarbageBag(String desc) {
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "GarbageBag{" +
                "desc='" + desc + '\'' +
                '}';
    }
}