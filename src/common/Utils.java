package common;

import java.util.function.Supplier;

public class Utils {

    public static void measureTime(Runnable task){
        long start = System.nanoTime();

        task.run();

        long end = System.nanoTime();

        System.out.println(
            " 执行耗时 " + 
            (end-start) / 1_000_000.0 + 
            "ms"
        );
    }

    public static <T> T measureTime(Supplier<T> task){
        long start = System.nanoTime();

        T result = task.get();

        long end = System.nanoTime();

        System.out.println(
            " 执行耗时 " + 
            (end-start) / 1_000_000.0 + 
            "ms"
        );

        return result;
    }

}
