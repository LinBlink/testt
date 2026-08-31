package pure_test.course05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Course05 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        // Consumer<T>：有入参，无返回 —— 对每个值做点事
        each(nums, n -> System.out.print("[" + n + "] "));
        System.out.println("\n-----");

        // Predicate<T>：有入参，返回 boolean —— 过滤/判断
        printFiltered(nums, n -> n % 2 == 0);
        System.out.println("-----");

        // Function<T,R>：有入参，有返回 —— 转换/映射
        List<String> strings = map(nums, n -> "v" + n);
        System.out.println(strings);
        System.out.println("-----");

        // Supplier<T>：无入参，有返回 —— 提供/生产值
        System.out.println("nums为空?  默认值-> " + firstOr(nums, () -> 999));
        System.out.println("空列表? 默认值-> " + firstOr(List.of(), () -> 999));
    }

    static void each(List<Integer> nums, Consumer<Integer> c) {
        for (int n : nums) {
            c.accept(n);
        }
    }

    static void printFiltered(List<Integer> nums, Predicate<Integer> p) {
        for (int n : nums) {
            if (p.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }

    static List<String> map(List<Integer> nums, Function<Integer, String> f) {
        List<String> result = new ArrayList<>();
        for (int n : nums) {
            result.add(f.apply(n));
        }
        return result;
    }

    static int firstOr(List<Integer> nums, Supplier<Integer> s) {
        return nums.isEmpty() ? s.get() : nums.get(0);
    }
}