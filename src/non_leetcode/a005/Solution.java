package non_leetcode.a005;

import java.util.Arrays;

/*
合并两个有序数组：给定两个升序数组 a 和 b，合并为一个新的升序数组返回。要求时间复杂度
O(m+n)，不允许先拼接再排序。
示例：a = [1, 3, 5]，b = [2, 4, 6] → [1, 2, 3, 4, 5, 6]。
*/

public class Solution {
    public static void main(String[] args) {
        int[] a = {1,3,5};
        int[] b = {2,4,6};
        int[] result = method(a, b);
        System.out.println( Arrays.toString( result ) );
    }

    public static int[] method( int[] a, int[] b ){
        
        int i = 0, j = 0, k = 0 ;
        int[] result  = new int[a.length+b.length];
        
        while( i<a.length && j<b.length ){
            if( a[i] <= b[j] ){
                result[k++] = a[i++];
            }else{
                result[k++] = b[j++];
            }
        }

        while( i<a.length ){
            result[k++] = a[i++];
        }

        while( j<b.length ){
            result[k++] = b[j++];
        }


        return result;

    }
}
