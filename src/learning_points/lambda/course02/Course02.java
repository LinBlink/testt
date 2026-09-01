package learning_points.lambda.course02;

import java.util.List;

public class Course02 {
    public static void main(String[] args) {
        // 复用第1课的 printFiltered，但这次不单独建类
        printFiltered(List.of(1, 2, 3, 4, 5), new Filter() {
            @Override
            public boolean accept(int value) {
                return value % 3 == 0;   // 能被3整除
            }
        });
        System.out.println("-----");
        printFiltered(List.of(1, 2, 3, 4, 5), new Filter() {
            @Override
            public boolean accept(int value) {
                return value > 3;        // 大于3
            }
        });
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