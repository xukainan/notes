package top.uaian.algorithm.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        int[] result = bubbleSort(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i] + " ");
        }
    }

    private static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    arr[i] ^= arr[j];
                    arr[j] ^= arr[i];
                    arr[i] ^= arr[j];
                }
            }
        }
        return arr;
    }

//    public static void main(String[] args) {
//        int a = 1;
//        int b = 2;
//        a^=b;
//        b^=a;
//        a^=b;
//        System.out.println("a:" + a + "b:" + b);
//    }
}
