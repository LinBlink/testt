package leetcode.a004;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



class Solution {


    /*
    给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    */
    
    public static void main(String[] args) {
        
        int result = mymethod1( new int[]{-3,2,-3} );
        System.out.println( result );
        
    }
    
    public static int mymethod1(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if ( count == 0 ) {
                candidate = num;
            }
            if( num == candidate ){
                count ++;
            }else{
                count --;
            }
        }

        return candidate;

    }


    public static int bestmethod(int[] nums){
        return 0;
    }

    

    
}