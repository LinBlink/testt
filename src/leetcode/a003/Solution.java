package leetcode.a003;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    /*
    给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。

    考虑 nums 的唯一元素的数量为 k。去重后，返回唯一元素的数量 k。

    nums 的前 k 个元素应包含 排序后 的唯一数字。下标 k - 1 之后的剩余元素可以忽略。

    eg.
    输入：nums = [1,1,2]
    输出：2, nums = [1,2,_]
    解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 */
    
    public static void main(String[] args) {
        
        int result = bestmethod( new int[]{-3,-1,0,0,0,3,3} );
        System.out.println( result );
        
    }
    
    public static int mymethod1(int[] nums) {

        int order = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            // 如果是第一次遇到，加入 set
            if ( !map.values().contains( nums[i] ) ) {
                map.put(order++, nums[i]);
            }
        
        }

        for (int i = 0; i < order; i++) {
            nums[i] = map.get(i);
        }

        System.out.println( Arrays.toString(nums) );

        return order;

    }

    public static int mymethod2(int [] nums){

        Set<Integer> set = new HashSet<>();

        int order = 0;

        for (int i = 0; i < nums.length; i++) {
            if(!set.contains(nums[i])){
                set.add(nums[i]);
                nums[order++] = nums[i];
            }
        }

        return order;

    }

    public static int bestmethod(int[] nums){
        
        // 核心优化：由于非严格递增，所以问这个数字有没有出现过
        // 等同于在问：这个数字的上一个数字是不是还是它？

        int order = 1;

        /*
            order = 1
            nums[1] != nums[0]
            nums[1] = nums[0]

        */

        for (int i = 1; i < nums.length; i++) {
            
            if(nums[i] != nums[i-1]){
                nums[order++] = nums[i];
            }

        }

        return order;


    }

    

    
}