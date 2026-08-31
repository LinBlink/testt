package pure_test.course04;

import java.util.List;

public class Course04 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        // 形式1：完整写法（显式类型 + 括号 + 大括号 + return）
        printFiltered(nums, (int value) -> {
            return value % 2 == 0;
        });
        System.out.println("-----");

        // 形式2：单表达式，return、大括号、类型、参数括号全可省
        printFiltered(nums, value -> value % 3 == 0);
        System.out.println("-----");

        // 形式3：多语句就必须写大括号和 return
        printFiltered(nums, value -> {
            if (value < 3) {
                return false;
            }
            return value % 2 == 0;
        });
        System.out.println("-----");

        // 变量捕获：lambda 里能用方法外部的变量，但它不能再被修改
        int min = 2;
        printFiltered(nums, value -> value >= min);
        System.out.println("-----");

        // 无参数 lambda：Runnable
        new Thread(() -> System.out.println("runnable lambda")).start();
    }

    static void printFiltered(List<Integer> nums, Filter f) {
        for (int n : nums) {
            if (f.accept(n)) {
                System.out.println(n);
            }
        }
    }
}

interface Filter {
    boolean accept(int value);
}