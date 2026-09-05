package non_leetcode.a001_merge_two_sorted_arrays;

import common.Utils;
import java.util.Arrays;

public class Solution {

    /*
        合并两个有序数组：给定两个升序数组 a 和 b，合并为一个新的升序数组返回。要求时间复杂度
O(m+n)，不允许先拼接再排序。
示例：a = [1, 3, 5]，b = [2, 4, 6] → [1, 2, 3, 4, 5, 6]。
    */

    public static void main(String[] args) {
        int[] a = {1,3,5};
        int[] b = {2,4,6};

        System.out.println( 
            Arrays.toString( 
                Utils.measureTime( 
                    ()->mysolve(a, b) 
                )
            ) 
        );

        System.out.println( 
            Arrays.toString( 
                Utils.measureTime( 
                    ()->goodsolve(a, b) 
                )
            ) 
        );

    }

    public static int[] goodsolve(int[] a, int[] b){

        int[] c = new int[a.length + b.length];

        int i = 0 , j = 0, k = 0;

        // 两边都还有元素
        while( i<a.length && j<b.length ){
            // 数组a目标元素小于b的目标元素，a目标元素放入c
            if(a[i]<=b[j]){
                c[k]=a[i];
                i++;
            // 数组a目标元素大于b的目标元素，b目标元素放入c
            }else{
                c[k]=b[j];
                j++;
            }
            k++;
        }
        
        // a还有剩余
        while( i<a.length ){
            c[k] = a[i];
            i++;
            k++;
        }

        // b还有剩余
        while( j<b.length ){
            c[k] = b[j];
            j++;
            k++;
        }

        return c;

    }

    public static int[] mysolve( int[] a, int[] b ){

        int[] c = new int[ a.length + b.length ];

        int i=0, j=0, k=0;

        while( k<c.length ){


            // 如果数组a放完了，剩下的全放b
            if( i > a.length-1 ){
                c[k]=b[j];
                j++;
                k++;
                continue;
            }
            // 如果数组b放完了，剩下的全放a
            if( j > b.length -1 ){
                c[k]=a[i];
                i++;
                k++;
                continue;
            }

            // 数组a目标元素小于b的目标元素，a目标元素放入c
            if(a[i]<=b[j]){
                c[k]=a[i];
                i++;
            // 数组a目标元素大于b的目标元素，b目标元素放入c
            }else{
                c[k]=b[j];
                j++;
            }
            k++;

        }

        return c;
    }
}
