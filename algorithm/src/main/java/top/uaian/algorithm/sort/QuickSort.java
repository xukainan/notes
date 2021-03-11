package top.uaian.algorithm.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(start < end){
            int stand = arr[start];
            int high = end;
            int low = start;
            while (low < high){
                while (low < high && arr[high] > stand){
                    high--;
                }
                arr[low] = arr[high];
                while (low < high && arr[low] < stand){
                    low ++;
                }
                arr[high] = arr[low];
            }
            arr[low] = stand;
            quickSort(arr, start, low);
            quickSort(arr, low + 1, end);
        }


    }

}
