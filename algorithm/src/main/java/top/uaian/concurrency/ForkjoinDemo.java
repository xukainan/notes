package top.uaian.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkjoinDemo {

    private static final Integer max = 200;

    static class MainForkJoinTask extends RecursiveTask<Integer>{

        private Integer startVal;
        private Integer endVal;

        public MainForkJoinTask(Integer startVal, Integer endVal) {
            this.startVal = startVal;
            this.endVal = endVal;
        }

        @Override
        protected Integer compute() {
            if(endVal - startVal < max){
                System.out.println("开始计算的部分：" + startVal + " , 结束的部分：" + endVal);
                Integer totalVal = 0;
                for (int index = this.startVal; index <= this.endVal; index++) {
                    totalVal += index;
                }
                return totalVal;
            }else {
                MainForkJoinTask mainForkJoinTask1 = new MainForkJoinTask(startVal, (startVal + endVal) / 2);
                mainForkJoinTask1.fork();
                MainForkJoinTask mainForkJoinTask2 = new MainForkJoinTask((startVal + endVal) / 2 + 1, endVal);
                mainForkJoinTask2.fork();
                return mainForkJoinTask1.join() + mainForkJoinTask2.join();
            }
        }
    }


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new MainForkJoinTask(1, 1001));
        try {
            Integer result = task.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);

        }
    }


}
