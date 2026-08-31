package leetcode.a001合并两个有序数组;

import java.util.Arrays;

/**
 * @description: 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * @author: LOCRIAN_V
 * @date: 8/17/2026 2:11 PM
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums1 = {2,4,6,0,0,0,0,0};
        int[] nums2 = {1,3,5,7,9};
        merge(
                nums1,
                nums1.length-nums2.length,
                nums2,
                nums2.length
        );
        System.out.println(
                Arrays.toString(
                        nums1
                )
        );
    }

    public static void merge( int[] nums1, int m, int[] nums2, int n ){

        int[] result = new int[m+n];

        // i 为nums1遍历游标， j为nums2遍历游标， k为result遍历游标
        int i = 0, j=0, k=0 ;

        while(true){

            // i，j 都没有越限
            if( i<m && j<n ){
                if (nums1[i] < nums2[j]) {
                    result[k] = nums1[i];
                    i++;
                }else{
                    result[k] = nums2[j];
                    j++;
                }
                // i越限，说明nums1取值结束，下面直接放nums2
            } else if ( i>=m && j<n ) {
                result[k] = nums2[j];
                j++;
                // j越限，说明nums2取值结束，下面直接放nums1
            } else if( i<m && j>=n ){
                result[k] = nums1[i];
                i++;
                // k取值达到m+n，表示循环结束
            } else if( k>= m+n ){
                break;
            }

            k++;
        }

        // 将 result 填回 nums1
        System.arraycopy(
                result,
                0,
                nums1,
                0,
                result.length
        );


    }
}
