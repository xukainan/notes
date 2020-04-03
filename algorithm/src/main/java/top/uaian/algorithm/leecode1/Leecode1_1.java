package top.uaian.algorithm.leecode1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leecode1_1 {
    public static void main(String[] args) {
        int target = 10;
        int[] nums = new int[]{2,5,5,11};
        List<Integer> result = new LinkedList<>();
        result = toSum(nums, result, target);
        int[] arr = result.stream().mapToInt(k->k).toArray();
        System.out.println(Arrays.toString(arr));
    }

    private static List toSum(int[] nums, List result, int target) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int t = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i) != null) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j) != null) {
                        if (nums[i] + nums[j] == target) {
                            result.add(i);
                            result.add(j);
                            list.set(j,null);
                        }
                    }

                }
            }
        }
        return result;
    }
}
