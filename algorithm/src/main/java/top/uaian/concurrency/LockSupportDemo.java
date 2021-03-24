package top.uaian.concurrency;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            LockSupport.parkNanos(1000 * 1000000);

        }
    }
}
