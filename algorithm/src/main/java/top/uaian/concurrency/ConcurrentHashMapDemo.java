package top.uaian.concurrency;

import top.uaian.algorithm.hashmap.HashMap;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
//        HashMap<Integer, Integer> map = new HashMap<>();

        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 20; i++) {
            Executors.newFixedThreadPool(20).execute(() -> {
                map.put(atomicInteger.get(), atomicInteger.getAndAdd(1));
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
