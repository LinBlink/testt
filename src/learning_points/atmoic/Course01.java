package learning_points.atmoic;

import java.util.concurrent.atomic.AtomicInteger;

public class Course01 {
    public static void main(String[] args) {
    
        AtomicInteger number = new AtomicInteger(10);
        boolean success = number.compareAndSet(10, 20);;
        System.out.println(success);
        System.out.println(number.get());
        number.incrementAndGet();
        System.out.println(number.get());
    
    }
}
