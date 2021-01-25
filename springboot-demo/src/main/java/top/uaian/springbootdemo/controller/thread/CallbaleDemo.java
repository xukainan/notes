package top.uaian.springbootdemo.controller.thread;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * description:  <br>
 * date: 2021/1/25 14:47 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class CallbaleDemo implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallbaleDemo callbaleDemo = new CallbaleDemo();
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Boolean> result = new FutureTask<Boolean>(callbaleDemo);
        new Thread(result).start();
        System.out.println(result.get());
    }
}
