package top.uaian.algorithm.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10,5,12,7,16,75,26,2,1,6,49,32,8};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    private static void mergeSort(int[] arr, int start, int end, int[] temp) {
        if(start < end){
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid, temp);
            mergeSort(arr, mid+1, end, temp);
            sortMergeArr(arr, start, mid, end, temp);
        }
    }

    private static void sortMergeArr(int[] array, int start, int mid, int end, int[] temp) {
        int i = start, j = mid + 1; // i为第一组的起点, j为第二组的起点
        int m = mid, n = end; // m为第一组的终点, n为第二组的终点
        int k = 0; // k用于指向temp数组当前放到哪个位置
        while (i <= m && j <= n) { // 将两个有序序列循环比较, 填入数组temp
            if (array[i] <= array[j]){
                temp[k++] = array[i++];
            } else{
                temp[k++] = array[j++];
            }
        }
        while (i <= m) { // 如果比较完毕, 第一组还有数剩下, 则全部填入temp
            temp[k++] = array[i++];
        }
        while (j <= n) {// 如果比较完毕, 第二组还有数剩下, 则全部填入temp
            temp[k++] = array[j++];
        }
        for (i = 0; i < k; i++) {// 将排好序的数填回到array数组的对应位置
            array[start + i] = temp[i];
        }
    }
}
