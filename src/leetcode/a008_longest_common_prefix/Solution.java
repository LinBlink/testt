package leetcode.a008_longest_common_prefix;


class Solution {


    /*
    编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

 

示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
 

提示：

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] 如果非空，则仅由小写英文字母组成

    */
    
    public static void main(String[] args) {
        
        String result = mymethod1( new String[]{"flower", "flow", "flight"} );
        System.out.println( result );
        
    }

    public static String mymethod1(String[] strs) {

        // 找到第0个String，以此和数组其他String比对，比对都没问题，就是公共前缀
        String compareStr = strs[0];

        int minLength = strs[0].length();

        // 找到长度最小的str
        for (String str : strs) {
            if (str.length()<minLength) {
                minLength = str.length();
            }
        }

        // 进行返回的字符串
        StringBuffer result = new StringBuffer("");

        // 单个字符前缀成功匹配的字符串个数
        int matchCount = 1;

        // 单个匹配的字符临时变量;
        char temp = ' ';

        for (int i = 0; i < minLength; i++) {

            // 本轮需要比较的字符
            temp = compareStr.charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if( strs[j].charAt(i) == temp ){
                    matchCount++;
                }
            }

            // 单个字符匹配成功
            if (matchCount==strs.length) {
                result.append( temp );
                // 重置匹配数量
                matchCount = 1;
            }else{ // 单个字符匹配失败
                break;
            }

        }

        return result.toString();


    }

    public static String bestPractice(String[] strs){
        
        String first = strs[0];

        int minLength = first.length();

        for (String str : strs) {
            minLength = Math.min( minLength, str.length() );
        }

        for (int i = 0; i < minLength; i++) {

            char c = first.charAt(i);

            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c ) {
                   return first.substring(0, i); 
                }
            }

        }

        return first.substring(0, minLength);

    }
    

    
}