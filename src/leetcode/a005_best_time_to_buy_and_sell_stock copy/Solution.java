package leetcode.a005_best_time_to_buy_and_sell_stock;

class Solution {


    /*
    给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    */
    
    public static void main(String[] args) {
        
        System.out.println("有利可图");
        int result = mymethod2( new int[]{7,1,5,3,6,4} );
        System.out.println( result );

        System.out.println("无利可图");
        result = mymethod2( new int[]{7,6,4,3,1} );
        System.out.println( result );
        
    }
    
    public static int mymethod1(int[] nums) {
        // 在所有数字最左边找到最小数字，在所有数字最右边找到最大数字
        // 两个指针，一个左边找最小，一个右边找最大

        int profit = 0;
        int gap = 0;
        
        for (int i = 0; i < nums.length; i++) {
        for (int j = i+1; j < nums.length; j++) {
            gap = nums[j] - nums[i];
            if ( gap > 0 && gap > profit ){
                profit = gap;
            }
        }
        }

        return profit;
    }

    public static int mymethod2(int[] nums) {

        int profit = 0;

        int lowest_price = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]<lowest_price) {
                lowest_price = nums[i]; // 发现新史低价格就更新史低
            }
            // 今天卖掉的话赚的比之前统计的profit多，就更新profit
            if ( nums[i] - lowest_price > profit  ) {
                profit = nums[i] - lowest_price ;
            }
        }

        return profit;
    }


    public static int bestmethod(int[] nums){
        return 0;
    }

    

    
}