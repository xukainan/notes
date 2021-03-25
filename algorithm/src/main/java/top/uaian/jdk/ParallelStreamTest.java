package top.uaian.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelStreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        long start1 = System.currentTimeMillis();
        AtomicInteger sum = new AtomicInteger(0);
        list.stream().forEach(integer -> {
            sum.addAndGet(integer);
        });
        System.out.println("stream耗时：" + (System.currentTimeMillis() - start1) + "，sum：" + sum.get());
        long start2 = System.currentTimeMillis();
        AtomicInteger sum2 = new AtomicInteger(0);
        list.parallelStream().forEach(integer -> {
            sum2.addAndGet(integer);
        });
        System.out.println("stream耗时：" + (System.currentTimeMillis() - start2) + "，sum：" + sum2.get());
    }
}
