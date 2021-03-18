package top.uaian.concurrency;

import java.util.Arrays;
import java.util.concurrent.*;

public class ForkJoinMergeSort {

    static class MainForkJoinTask extends RecursiveTask<int[]> {

        int[] arr;

        public MainForkJoinTask(int[] arr) {
            this.arr = arr;
        }

        @Override
        protected int[] compute() {
            int arrLen = arr.length;
            if(arrLen <=2){
                if(arrLen == 1 || arr[0] <= arr[1]) {
                    return arr;
                } else {
                    int targetp[] = new int[arrLen];
                    targetp[0] = arr[1];
                    targetp[1] = arr[0];
                    return targetp;
                }

            }else {
                int midIndex = arrLen / 2;
                MainForkJoinTask mainForkJoinTask1 = new MainForkJoinTask(Arrays.copyOf(arr, midIndex));
                MainForkJoinTask mainForkJoinTask2 = new MainForkJoinTask(Arrays.copyOfRange(arr, midIndex , arrLen));
                mainForkJoinTask1.fork();
                mainForkJoinTask2.fork();
                int[] join1 = mainForkJoinTask1.join();
                int[] join2 = mainForkJoinTask2.join();
                return sortMerge(join1, join2);
            }
        }

        private int[] sortMerge(int[] join1, int[] join2) {
            int[] temp = new int[join1.length + join2.length];
            int k = 0, start1 = 0, start2 = 0;
            int end1 = join1.length-1, end2 = join2.length-1;
            while (start1 <= end1 && start2 <= end2){
                if(join1[start1] <= join2[start2]){
                    temp[k++] = join1[start1++];
                }else {
                    temp[k++] = join2[start2++];
                }
            }
            while (start1 <= end1){
                temp[k++] = join1[start1++];
            }
            while (start2 <= end2){
                temp[k++] = join2[start2++];
            }
            return temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        ForkJoinPool pool = new ForkJoinPool();
        MainForkJoinTask mainForkJoinTask = new MainForkJoinTask(arr);
        ForkJoinTask<int[]> submit = pool.submit(mainForkJoinTask);
        try {
            int[] ints = submit.get();
            for (int i = 0; i < ints.length; i++) {
                System.out.println(ints[i] + " ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
