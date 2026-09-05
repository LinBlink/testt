package leetcode.a002_remove_element;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。

假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：

更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
返回 k。

 * Solution
 */

public class Solution {
    public static void main(String[] args) {
        int rst = removeElement( new int[]{1,2,3,3,2,1} , 3);
        System.out.println(rst);
    }

    public static int removeElement(int[] nums, int val) {

        // 指向第0个元素
        int k = 0;

        // 一个个扫描num
        for (int num : nums) {
            // 每扫描到一个不为val的num
            if (num != val) {
                // 将该num放入数组第k个，k自增
                nums[k++] = num;
            }
        }

        System.out.println(
            "整理后的数组：" + Arrays.toString(nums)
        );

        return k;
    }
}