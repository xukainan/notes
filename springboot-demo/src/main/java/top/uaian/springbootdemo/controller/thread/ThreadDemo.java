package top.uaian.springbootdemo.controller.thread;

import java.time.LocalDateTime;

/**
 * description:  <br>
 * date: 2021/1/25 14:04 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class ThreadDemo extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
