package leetcode.a007_length_of_last_word;

class Solution {


    /*
给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

示例 1：

输入：s = "Hello World"
输出：5
解释：最后一个单词是“World”，长度为 5。
示例 2：

输入：s = "   fly me   to   the moon  "
输出：4
解释：最后一个单词是“moon”，长度为 4。
示例 3：

输入：s = "luffy is still joyboy"
输出：6
解释：最后一个单词是长度为 6 的“joyboy”。
 
    */
    
    public static void main(String[] args) {
        
        System.out.println("冒烟测试");
        int result = mymethod1( "a" );
        System.out.println( result );

        // System.out.println("复杂测试");
        // result = mymethod1( "Fly me to the moon" );
        // System.out.println( result );
        
    }
    
    public static int mymethod1(String s) {

        // 从最后一个字符往前找空格，找到就停止计数

        int count = 0;

        int i = s.length() -1 ;

        while( i>=0 && s.charAt(i) == ' ' ){
            i--;
        }

        while ( i>=0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
    
}