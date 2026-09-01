package learning_points.lambda.course01;

import java.util.List;

public class Course01 {
    public static void main(String[] args) {
        printFiltered(List.of(1, 2, 3, 4, 5), new EvenFilter());
        System.out.println("-----");
        printFiltered(List.of(1, 2, 3, 4, 5), new OddFilter());
    }

    // 2. 方法参数是接口类型：任何实现它的对象都能传进来
    static void printFiltered(List<Integer> nums, Filter f) {
        for (int n : nums) {
            if (f.accept(n)) {
                System.out.println(n);
            }
        }
    }
}

// 1. 接口：只声明"要做什么"，不写"怎么做"
interface Filter  {
    boolean accept(int value);
}

// 两个"怎么做"各异的不同实现
class EvenFilter implements Filter {
    public boolean accept(int value) {
        return value % 2 == 0;
    }
}

class OddFilter implements Filter {
    public boolean accept(int value) {
        return value % 2 == 1;
    }
}