package top.uaian.algorithm.sort;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        insertSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    private static void insertSort(int[] arr) {
        for (int j = 0; j < arr.length-1; j++) {
            int waitSortNum = j + 1;
            for (int i = j; i >= 0; i--) {
                if(waitSortNum >=1 && arr[i] > arr[waitSortNum]){
                    arr[i] ^= arr[waitSortNum];
                    arr[waitSortNum] ^= arr[i];
                    arr[i] ^= arr[waitSortNum];
                    waitSortNum--;
                }else {
                    break;
                }
            }
        }
    }
}
