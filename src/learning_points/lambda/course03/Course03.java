package learning_points.lambda.course03;

import java.util.List;

public class Course03 {
    public static void main(String[] args) {
        // 写法A：匿名内部类（第2课学过的）
        printFiltered(List.of(1, 2, 3, 4, 5), new Filter() {
            @Override
            public boolean accept(int value) {
                return value % 2 == 0;
            }
        });
        System.out.println("-----");
        // 写法B：Lambda（第4课才展开，这里先让你看效果）
        printFiltered(List.of(1, 2, 3, 4, 5),
                value -> value % 3 == 0
        );
    }

    static void printFiltered(List<Integer> nums, Filter f) {
        for (int n : nums) {
            if (f.accept(n)) {
                System.out.println(n);
            }
        }
    }
}

@FunctionalInterface
interface Filter {
    boolean accept(int value);
}