package top.uaian.algorithm.sort;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        int[] result = bucketSort(arr);
        for (int i = 0; i < result.length; i++) {
            if(result[i] != 0){
                System.out.println(result[i] + " ");
            }
        }
    }

    private static int[] bucketSort(int[] arr) {
        int[] result = new int[100];
        for (int i = 0; i < arr.length; i++) {
            result[arr[i]] = arr[i];
        }
        return result;
    }
}
