package leetcode.a009_find_the_index_of_the_first_occurrence_in_a_string;


class Solution {


    /*
    给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。

    示例 1：

    输入：haystack = "sadbutsad", needle = "sad"
    输出：0
    解释："sad" 在下标 0 和 6 处匹配。
    第一个匹配项的下标是 0 ，所以返回 0 。
    示例 2：

    输入：haystack = "leetcode", needle = "leeto"
    输出：-1
    解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
    

    提示：

    1 <= haystack.length, needle.length <= 104
    haystack 和 needle 仅由小写英文字符组成

    */
    
    public static void main(String[] args) {
        
        int result = mymethod1( "goodmanbadman", "man" );
        System.out.println( result );
        
    }
    
    public static int mymethod1(String haystack, String needle) {


        // 思路：两个指针，从左到右匹配 haystack。
        for (int i = 0; i < haystack.length()-needle.length()+1 ; i++) {


            for (int j = 0; j < needle.length(); j++) {

                if( haystack.charAt(i+j) != needle.charAt(j) ){
                    break;
                }

                if ( j==needle.length()-1 ) {
                    return i;
                }
            }

        }

        return -1;
    }

    public static int goodPractice(String haystack, String needle) {
        
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n-m ; i++) {
            int j = 0;
            while ( j < m && haystack.charAt(i+j) == needle.charAt(j) ) {
                j++;
            }
            if (j==m) {
                return i;
            }
        }

        return -1;

    }

    public static int bestPractice(String haystack, String needle){
        return 0;
    }

    /**
     * 构建 KMP 算法的 LPS（Longest Prefix Suffix）数组。
     *
     * lps[i] 表示：
     * pattern[0...i] 这个子串中，
     * 最长的“相同真前缀和真后缀”的长度。
     */
    public static int[] buildLps(String pattern) {

        int[] lps = new int[pattern.length()];

        // 当前已经匹配成功的前缀长度
        int prefixLength = 0;

        // lps[0] 一定是 0，所以从下标 1 开始计算
        int currentIndex = 1;

        while (currentIndex < pattern.length()) {

            char currentChar = pattern.charAt(currentIndex);
            char prefixChar = pattern.charAt(prefixLength);

            // 当前字符能继续扩展已有的前缀匹配
            if (currentChar == prefixChar) {
                prefixLength++;
                lps[currentIndex] = prefixLength;
                currentIndex++;
                continue;
            }

            // 匹配失败，但之前存在已匹配的前缀：
            // 尝试缩短前缀长度，继续匹配
            if (prefixLength > 0) {
                prefixLength = lps[prefixLength - 1];
                continue;
            }

            // 已经退无可退，当前位置不存在相同前后缀
            lps[currentIndex] = 0;
            currentIndex++;
        }

        return lps;
    }

    

    
}