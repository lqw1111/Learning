package JVM;

import java.util.ArrayList;
import java.util.List;

public class GC_Analysis {
    private static final int _512KB = 512 * 1024;
    private static final int _1MB = 1024 * 1024;
    private static final int _6MB = 6 * 1024 * 1024;
    private static final int _7MB = 7 * 1024 * 1024;
    private static final int _8MB = 8 * 1024 * 1024;

    // -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) throws InterruptedException {

        // 线程内的内存outofMemory不会导致主线程的内存溢出
        example3();
        Thread.sleep(1000L);
    }

    private static void example1(){
        List<byte[]> list = new ArrayList<>();

        list.add(new byte[_7MB]);
        list.add(new byte[_512KB]);
        list.add(new byte[_512KB]);

        //大对象直接晋升老年代,不触发垃圾回收
//        list.add(new byte[_8MB]);

        //如果再加一个大对象
//        list.add(new byte[_8MB]);
    }

    private static void example2(){
        List<byte[]> list = new ArrayList<>();

        //大对象直接晋升老年代,不触发垃圾回收
        list.add(new byte[_8MB]);

        //如果再加一个大对象
        list.add(new byte[_8MB]);
    }

    private static void example3(){
        new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            list.add(new byte[_8MB]);
            list.add(new byte[_8MB]);
        }).start();
    }
}
